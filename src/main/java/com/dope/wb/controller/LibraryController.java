package com.dope.wb.controller;

import com.dope.wb.dto.LibraryDetailResponseDto;
import com.dope.wb.service.board.LibraryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "library")
public class LibraryController {

    LibraryServiceImpl libraryService;

    @GetMapping("/{serial}")
    public ResponseEntity<LibraryDetailResponseDto> readProduct(@PathVariable String serial) {
        LibraryDetailResponseDto libraryDetailResponseDto = libraryService.readDetail(serial);
        return ResponseEntity.ok().body(libraryDetailResponseDto);
    }
}
