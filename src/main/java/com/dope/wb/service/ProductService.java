package com.dope.wb.service;

import com.dope.wb.domain.product.Product;
import com.dope.wb.domain.product.ProductImage;
import com.dope.wb.domain.product.ProductSketch;
import com.dope.wb.dto.ProductUploadRequestDto;
import com.dope.wb.repository.ProductImageRepository;
import com.dope.wb.repository.ProductRepository;
import com.dope.wb.repository.ProductSketchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductSketchRepository productSketchRepository;

    @Value("${app.resource.product.image}")
    private String productImageBaseDir;
    @Value("${app.resource.product.sketch}")
    private String productSketchBaseDir;

    @Transactional
    public void uploadNewProduct(ProductUploadRequestDto productUploadRequestDto) {
        Product product = Product.builder()
                .serial(productUploadRequestDto.getSerial())
                .content(productUploadRequestDto.getContent())
                .productCategory(productUploadRequestDto.getCategory())
                .build();
        productRepository.save(product);

        uploadProductImages(product, productUploadRequestDto.getImages());
        uploadProductSketch(product, productUploadRequestDto.getSketch());
    }

    private void uploadProductImages(Product product, List<MultipartFile> images) {
        for (MultipartFile image : images) {
            ProductImage productImage = ProductImage.builder().product(product).build();
            productImageRepository.save(productImage);
            Path filePath = productImage.createFilePath(product, image, productImageBaseDir);
            productImage.setPath(filePath.toAbsolutePath().toString());

            try {
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void uploadProductSketch(Product product, MultipartFile sketch) {
        ProductSketch productSketch = ProductSketch.builder().product(product).build();
        productSketchRepository.save(productSketch);
        Path filePath = productSketch.createFilePath(product, sketch, productSketchBaseDir);
        productSketch.setPath(filePath.toAbsolutePath().toString());

        try {
            Files.copy(sketch.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
