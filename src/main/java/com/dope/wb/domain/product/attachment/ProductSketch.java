package com.dope.wb.domain.product.attachment;

import com.dope.wb.domain.product.Product;
import javax.persistence.*;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Entity
public class ProductSketch extends ProductAttachment {

    @Builder
    public ProductSketch(Product product) {
        super(product);
    }

    protected ProductSketch() {
    }

    @Override
    public Path createFilePath(Product product, MultipartFile file, String basePath) {
        checkDir(basePath);
        String filename = file.getOriginalFilename();
        if(!filename.contains(".")) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invalid extension");
        }
        String extension = filename.substring(filename.indexOf(".")).toLowerCase(Locale.ROOT);
        if(extension.equals("jpg") || extension.equals("png") || extension.equals("gif") || extension.equals("pdf")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not supported file");
        }

        return Paths.get(basePath + File.separator + product.getSerial() + extension);
    }
}
