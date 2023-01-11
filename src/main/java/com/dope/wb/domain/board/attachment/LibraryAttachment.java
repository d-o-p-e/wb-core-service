package com.dope.wb.domain.board.attachment;

import com.dope.wb.domain.board.product.Product;
import lombok.Builder;

import javax.persistence.Entity;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Entity
public class LibraryAttachment extends Attachment{

    static final List<String> validExtension = null;

    protected LibraryAttachment() {
    }

    @Builder
    public LibraryAttachment(Product product) {
        super(product);
    }

    @Override
    public Path combinePath(String basePath, Product product, String extension) {
        return Paths.get(basePath + File.separator + product.getSerial() + this.getId() + extension);
    }

    @Override
    public void validateSupportedExtension(String extension) {
    }

}
