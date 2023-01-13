package com.dope.wb.service.board;

import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductUploadRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    void create(ProductUploadRequestDto productUploadRequestDto);

    ProductDetailResponseDto readDetail(String serial);

    void update(String serial);

    void delete(String serial);

    List<ProductDetailResponseDto> readBoardList(Pageable pageable);
}
