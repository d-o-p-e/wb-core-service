package com.dope.wb.domain.product.attachment;

import com.dope.wb.domain.product.Product;
import jakarta.persistence.Entity;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Entity
public class ProductImage extends ProductAttachment {

    @Builder
    public ProductImage(Product product) {
        super(product);
    }

    protected ProductImage() {
    }

    @Override
    public Path createFilePath(Product product, MultipartFile file, String basePath) {
        checkDir(basePath);
        String filename = file.getOriginalFilename();
        if(!filename.contains(".")) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invalid extension");
        }
        String extension = filename.substring(filename.indexOf(".")).toLowerCase(Locale.ROOT);
        if(!(extension.equals(".jpg") || extension.equals(".png") || extension.equals(".gif"))) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "unsupported file");
        }

        return Paths.get(basePath + File.separator + product.getSerial() + this.getId() + extension);
    }
}
