package com.dope.wb.service;

import com.dope.wb.domain.product.Product;
import com.dope.wb.dto.ProductUploadRequestDto;
import com.dope.wb.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void uploadNewProduct(ProductUploadRequestDto productUploadRequestDto) {
        Product product = Product.builder()
                .serial(productUploadRequestDto.getSerial())
                .content(productUploadRequestDto.getContent())
                .productCategory(productUploadRequestDto.getCategory())
                .build();
        productRepository.save(product);
    }
}
