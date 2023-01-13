package com.dope.wb.service.board;

import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductCreateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    void create(ProductCreateRequestDto productCreateRequestDto);

    ProductDetailResponseDto readDetail(String serial);

    void update(String serial);

    void delete(String serial);

    Page<ProductDetailResponseDto> readProductList(Pageable pageable);

}
