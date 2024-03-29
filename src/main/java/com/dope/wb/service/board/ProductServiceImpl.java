package com.dope.wb.service.board;

import com.dope.wb.domain.board.library.Library;
import com.dope.wb.domain.board.product.Product;
import com.dope.wb.domain.board.attachment.ProductImage;
import com.dope.wb.domain.board.attachment.ProductSketch;
import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductCreateRequestDto;
import com.dope.wb.repository.ProductImageRepository;
import com.dope.wb.repository.ProductRepository;
import com.dope.wb.repository.ProductSketchRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductSketchRepository productSketchRepository;

    @Value("${app.resource.product.image}")
    private String productImageBaseDir;
    @Value("${app.resource.product.sketch}")
    private String productSketchBaseDir;

    @Transactional
    @Override
    public void create(ProductCreateRequestDto productCreateRequestDto) {
        Product product = Product.builder()
                .serial(productCreateRequestDto.getSerial())
                .content(productCreateRequestDto.getContent())
                .productCategory(productCreateRequestDto.getCategory())
                .build();
        productRepository.save(product);

        if(productCreateRequestDto.getImages() != null) {
            uploadProductImages(product, productCreateRequestDto.getImages());
        }
        if(productCreateRequestDto.getSketch() != null) {
            uploadProductSketch(product, productCreateRequestDto.getSketch());
        }
    }

    @Override
    public ProductDetailResponseDto readDetail(String serial) {
        Product product = productRepository.findBySerial(serial).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid serial"));
        product.increaseView();
        List<ProductImage> images = productImageRepository.findAllByProduct(product);
        List<String> imagePaths = images.stream().map(ProductImage::getPath).collect(Collectors.toList());
        ProductSketch productSketch = productSketchRepository.findByProduct(product);
        return ProductDetailResponseDto.builder()
                .id(product.getId())
                .serial(product.getSerial())
                .content(product.getContent())
                .productCategory(product.getProductCategory())
                .view(product.getView())
                .imageList(imagePaths)
                .sketch(productSketch == null ? null : productSketch.getPath())
                .build();
    }

    @Override
    public void update(String serial) {

    }

    @Override
    public void delete(String serial) {

    }

    @Override
    public Page<ProductDetailResponseDto> readProductList(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);

        return result.map(product -> {
            List<String> imagesUrlList =
                    productImageRepository.findAllByProduct(product)
                    .stream()
                    .map(ProductImage::getPath)
                    .collect(Collectors.toList());

            ProductSketch sketch = productSketchRepository.findByProduct(product);

            return ProductDetailResponseDto.builder()
                    .id(product.getId())
                    .serial(product.getSerial())
                    .content(product.getContent())
                    .productCategory(product.getProductCategory())
                    .view(product.getView())
                    .imageList(imagesUrlList)
                    .sketch(sketch==null? null:sketch.getPath())
                    .build();
        });
    }

    private void uploadProductImages(Product product, List<MultipartFile> images) {
        for (MultipartFile image : images) {
            ProductImage productImage = new ProductImage(product);
            productImageRepository.save(productImage);
            Path filePath = productImage.createFilePath(product.getSerial(), image, productImageBaseDir);
            productImage.setPath(filePath.toString());

            try {
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void uploadProductSketch(Product product, MultipartFile sketch) {
        ProductSketch productSketch = new ProductSketch(product);
        productSketchRepository.save(productSketch);
        Path filePath = productSketch.createFilePath(product.getSerial(), sketch, productSketchBaseDir);
        productSketch.setPath(filePath.toString());

        try {
            Files.copy(sketch.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
