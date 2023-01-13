package com.dope.wb.controller;

import com.dope.wb.domain.board.library.Library;
import com.dope.wb.domain.board.product.Product;
import com.dope.wb.repository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class LibraryControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired LibraryRepository libraryRepository;

    @Test
    public void LibraryCreateSuccess() throws Exception {
        MockMultipartFile testImageFile = new MockMultipartFile(
                "attachments",
                "test.png",
                "image/png",
                new FileInputStream("app-resource/test/product/request/test.png"));

        mockMvc.perform(
                multipart("/library")
                        .file(testImageFile)
                        .param("title", "title")
                        .param("content", "content")
                        .param("category", (String) null)
        ).andExpect(status().isCreated());
    }

    @Test
    public void readProductDetailSuccess() throws Exception {
        Library library = Library.builder()
                .title("title")
                .build();
        libraryRepository.save(library);
        mockMvc.perform(get("/library/" + library.getId())).andExpect(status().isOk());
    }
}
