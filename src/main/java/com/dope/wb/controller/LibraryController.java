package com.dope.wb.controller;

import com.dope.wb.dto.LibraryCreateRequestDto;
import com.dope.wb.dto.LibraryDetailResponseDto;
import com.dope.wb.service.board.LibraryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "library")
public class LibraryController {

    private final LibraryServiceImpl libraryService;

    @PostMapping
    public ResponseEntity<Void> createProduct(LibraryCreateRequestDto libraryCreateRequestDto) {
        libraryService.create(libraryCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{libraryId}")
    public ResponseEntity<LibraryDetailResponseDto> readProduct(@PathVariable Long libraryId) {
        LibraryDetailResponseDto libraryDetailResponseDto = libraryService.readDetail(libraryId);
        return ResponseEntity.ok().body(libraryDetailResponseDto);
    }

}
