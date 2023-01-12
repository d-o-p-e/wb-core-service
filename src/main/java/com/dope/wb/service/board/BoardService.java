package com.dope.wb.service.board;

import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductUploadRequestDto;

public interface BoardService {

    void create(ProductUploadRequestDto productUploadRequestDto);

    ProductDetailResponseDto readDetail(String serial);

    void update(String serial);

    void delete(String serial);
}
