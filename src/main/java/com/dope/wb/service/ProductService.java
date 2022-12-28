package com.dope.wb.service;

import com.dope.wb.domain.product.Product;
import com.dope.wb.domain.product.ProductImage;
import com.dope.wb.dto.ProductUploadRequestDto;
import com.dope.wb.repository.ProductImageRepository;
import com.dope.wb.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${app.resource.product.image}")
    private String productImageBaseDir;

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Transactional
    public void uploadNewProduct(ProductUploadRequestDto productUploadRequestDto) {
        Product product = Product.builder()
                .serial(productUploadRequestDto.getSerial())
                .content(productUploadRequestDto.getContent())
                .productCategory(productUploadRequestDto.getCategory())
                .build();
        productRepository.save(product);

        uploadProductImages(product, productUploadRequestDto.getImages());
    }

    private void uploadProductImages(Product product, List<MultipartFile> images) {
        String baseDir = productImageBaseDir + File.separator;

        if (!Files.exists(Paths.get(baseDir))) {
            try {
                Files.createDirectories(Paths.get(baseDir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (MultipartFile image : images) {
            ProductImage productImage = ProductImage.builder().product(product).build();
            productImageRepository.save(productImage);
            String filename = image.getOriginalFilename();
            String extension = filename.substring(filename.indexOf(".")).toLowerCase(Locale.ROOT);
            if(extension.equals("jpg") || extension.equals("png") || extension.equals("gif")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not supported file");
            }

            try {
                Path copyOfLocation = Paths.get(baseDir + product.getSerial() + productImage.getId() + extension);
                Files.copy(image.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
                productImage.setNewFilePath(copyOfLocation.toAbsolutePath().toString());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
