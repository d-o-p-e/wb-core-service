package com.dope.wb.controller;

import com.dope.wb.dto.ProductUploadRequestDto;
import com.dope.wb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${apiPrefix}/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(ProductUploadRequestDto productUploadRequestDto) {
        productService.uploadNewProduct(productUploadRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
