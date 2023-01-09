package com.dope.wb.dto;

import com.dope.wb.specification.ProductCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductDetailResponseDto {

    private Long id;

    private String serial;

    private String content;

    private ProductCategory productCategory;

    private Long view;

    private List<String> imageList;

    private String sketch;
}
