package com.dope.wb.dto;

import com.dope.wb.specification.LibraryCategory;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class LibraryCreateRequestDto {

    private String title;

    private String content;

    private LibraryCategory category;

    private List<MultipartFile> attachments;

    @Builder
    public LibraryCreateRequestDto(String title, String content, LibraryCategory category, List<MultipartFile> attachments) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.attachments = attachments;
    }
}
