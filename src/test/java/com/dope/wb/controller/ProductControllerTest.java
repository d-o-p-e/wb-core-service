package com.dope.wb.controller;

import com.dope.wb.domain.product.Product;
import com.dope.wb.dto.ProductUploadRequestDto;
import com.dope.wb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ProductRepository productRepository;

    @Test
    public void ProductCreateSuccess() throws Exception {
        MockMultipartFile testImageFile = new MockMultipartFile(
                "images",
                "test.png",
                "image/png",
                new FileInputStream("app-resource/test/product/request/test.png"));

        MockMultipartFile testPdfFile = new MockMultipartFile(
                "sketch",
                "test.pdf",
                "application/x-pdf",
                new FileInputStream("app-resource/test/product/request/test.pdf"));

        ProductUploadRequestDto productUploadRequestDto = ProductUploadRequestDto.builder()
                .serial("serial")
                .content("content")
                .category(null)
                .images(new ArrayList<>(List.of(testImageFile)))
                .sketch(testPdfFile)
                .build();

        mockMvc.perform(
                multipart("/api/product")
                .file(testImageFile)
                .file(testPdfFile)
                .param("serial", "serial")
                .param("content", "content")
                .param("category", (String) null)
            ).andExpect(status().isCreated());
    }

    @Test
    public void wrongFileTypeException() throws Exception {
        MockMultipartFile testImageFile = new MockMultipartFile(
                "images",
                "test",
                "image/png",
                new FileInputStream("app-resource/test/product/request/test"));

        MockMultipartFile testPdfFile = new MockMultipartFile(
                "sketch",
                "test",
                "application/x-pdf",
                new FileInputStream("app-resource/test/product/request/test.pdf"));

        ProductUploadRequestDto productUploadRequestDto = ProductUploadRequestDto.builder()
                .serial("serial")
                .content("content")
                .category(null)
                .images(new ArrayList<>(List.of(testImageFile)))
                .sketch(testPdfFile)
                .build();

        mockMvc.perform(
                multipart("/api/product")
                        .file(testImageFile)
                        .file(testPdfFile)
                        .param("serial", "serial")
                        .param("content", "content")
                        .param("category", (String) null)
        ).andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void readProductDetailSuccess() throws Exception {
        Product product = Product.builder()
                        .serial("testSerial")
                        .build();
        productRepository.save(product);
        mockMvc.perform(get("/api/product/testSerial")).andExpect(status().isOk());
    }

    @Test
    public void noSuchProductException() throws Exception {
        mockMvc.perform(get("/api/product/9999")).andExpect(status().isNotFound());
    }

}
