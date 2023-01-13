package com.dope.wb.dto;

import com.dope.wb.specification.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ProductCreateRequestDto {

    private String serial;

    private String content;

    private ProductCategory category;

    private List<MultipartFile> images;

    private MultipartFile sketch;

    @Builder
    public ProductCreateRequestDto(String serial, String content, ProductCategory category, List<MultipartFile> images, MultipartFile sketch) {
        this.serial = serial;
        this.content = content;
        this.category = category;
        this.images = images;
        this.sketch = sketch;
    }
}
