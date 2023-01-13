package com.dope.wb.service.board;

import com.dope.wb.dto.LibraryCreateRequestDto;
import com.dope.wb.dto.LibraryDetailResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Override
    public void create(LibraryCreateRequestDto libraryCreateRequestDto) {

    }

    @Override
    public LibraryDetailResponseDto readDetail(String serial) {
        return null;
    }

    @Override
    public void update(String serial) {

    }

    @Override
    public void delete(String serial) {

    }

    @Override
    public Page<LibraryDetailResponseDto> readLibraryList(Pageable pageable) {
        return null;
    }
}
