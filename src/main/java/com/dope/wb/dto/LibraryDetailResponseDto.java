package com.dope.wb.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LibraryDetailResponseDto {

    private Long id;

    private String title;

    private String content;

    private Long view;

    private List<String> attachmentList;
}
