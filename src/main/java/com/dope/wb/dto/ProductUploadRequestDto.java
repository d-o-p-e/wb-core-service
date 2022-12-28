package com.dope.wb.dto;

import com.dope.wb.specification.ProductCategory;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ProductUploadRequestDto {

    private String serial;

    private String content;

    private ProductCategory category;

    private List<MultipartFile> images;

    private MultipartFile sketch;
}
