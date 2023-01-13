package com.dope.wb.domain.board.attachment;

import com.dope.wb.domain.board.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;

class AttachmentTest {

    static String testPath = "app-resource/test";

    @Test
    public void createFilePathSuccess() {
        ProductSketch productSketch = new ProductSketch();
        Product product = Product.builder()
                .serial("serial")
                .content("content")
                .productCategory(null)
                .build();
        MultipartFile mockMultipartFile = new MockMultipartFile("file", "test.pdf", "content-type", (byte[]) null);
        Path filePath = productSketch.createFilePath(product.getSerial(), mockMultipartFile, testPath);

        Assertions.assertEquals("app-resource/test/serial.pdf", filePath.toString());
    }

    @Test
    public void invalidFileExtensionException() {
        ProductImage productImage = new ProductImage();
        Product product = Product.builder()
                .serial("serial")
                .content("content")
                .productCategory(null)
                .build();
        MultipartFile mockMultipartFile = new MockMultipartFile("file", "test", "content-type", (byte[]) null);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            productImage.createFilePath(product.getSerial(), mockMultipartFile, testPath);
        });
    }

    @Test
    public void unsupportedFileExtensionException() {
        ProductImage productImage = new ProductImage();
        Product product = Product.builder()
                .serial("serial")
                .content("content")
                .productCategory(null)
                .build();
        MultipartFile mockMultipartFile = new MockMultipartFile("file", "test.abcd", "content-type", (byte[]) null);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            productImage.createFilePath(product.getSerial(), mockMultipartFile, testPath);
        });
    }

}