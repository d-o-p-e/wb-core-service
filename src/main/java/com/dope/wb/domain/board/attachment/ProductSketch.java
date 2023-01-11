package com.dope.wb.domain.board.attachment;

import com.dope.wb.domain.board.product.Product;
import javax.persistence.*;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Entity
public class ProductSketch extends Attachment {

    static final List<String> validExtension = Arrays.asList(".pdf");

    protected ProductSketch() {
    }

    @Builder
    public ProductSketch(Product product) {
        super(product);
    }

    @Override
    public Path combinePath(String basePath, Product product, String extension) {
        return Paths.get(basePath + File.separator + product.getSerial() + extension);
    }

    @Override
    public void validateSupportedExtension(String extension) {
        if (!validExtension.contains(extension)) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "unsupported file");
        }
    }

}
