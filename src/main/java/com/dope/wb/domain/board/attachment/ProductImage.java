package com.dope.wb.domain.board.attachment;

import com.dope.wb.domain.board.product.Product;

import javax.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Entity
public class ProductImage extends Attachment {

    static final List<String> validExtension = Arrays.asList(".jpg", ".png", ".gif");

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    protected ProductImage() {
    }

    public ProductImage(Product product) {
        this.product = product;
    }

    @Override
    public Path combinePath(String basePath, String specification, String extension) {
        return Paths.get(basePath + File.separator + product.getSerial() + product.getId() + extension);
    }

    @Override
    public void validateSupportedExtension(String extension) {
        if (!validExtension.contains(extension)) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "unsupported file");
        }
    }

}
