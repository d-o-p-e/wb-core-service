package com.dope.wb.service.board;

import com.dope.wb.dto.LibraryCreateRequestDto;
import com.dope.wb.dto.LibraryDetailResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibraryService {

    void create(LibraryCreateRequestDto libraryCreateRequestDto);

    LibraryDetailResponseDto readDetail(String serial);

    void update(String serial);

    void delete(String serial);

    Page<LibraryDetailResponseDto> readLibraryList(Pageable pageable);
}
