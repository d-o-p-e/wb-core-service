package com.dope.wb.controller;

import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductUploadRequestDto;
import com.dope.wb.service.board.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(ProductUploadRequestDto productUploadRequestDto) {
        productService.create(productUploadRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{serial}")
    public ResponseEntity<ProductDetailResponseDto> readProduct(@PathVariable String serial) {
        ProductDetailResponseDto productDetailResponseDto = productService.readDetail(serial);
        return ResponseEntity.ok().body(productDetailResponseDto);
    }
}
