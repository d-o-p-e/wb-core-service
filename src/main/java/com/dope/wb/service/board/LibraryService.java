package com.dope.wb.service.board;

import com.dope.wb.dto.ProductDetailResponseDto;
import com.dope.wb.dto.ProductUploadRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService implements BoardService {


    @Override
    public void create(ProductUploadRequestDto productUploadRequestDto) {

    }

    @Override
    public ProductDetailResponseDto readDetail(String serial) {
        return null;
    }

    @Override
    public void update(String serial) {

    }

    @Override
    public void delete(String serial) {

    }

    @Override
    public List<ProductDetailResponseDto> readBoardList(Pageable pageable) {
        return null;
    }
}
