package com.dope.wb.controller;

import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductCreateRequestDto;
import com.dope.wb.service.board.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "product")
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(ProductCreateRequestDto productCreateRequestDto) {
        productService.create(productCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{serial}")
    public ResponseEntity<ProductDetailResponseDto> readProduct(@PathVariable String serial) {
        ProductDetailResponseDto productDetailResponseDto = productService.readDetail(serial);
        return ResponseEntity.ok().body(productDetailResponseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ProductDetailResponseDto>> readProductList(Pageable pageable) {
        Page<ProductDetailResponseDto> productList = productService.readProductList(pageable);
        return ResponseEntity.ok().body(productList);
    }
}
