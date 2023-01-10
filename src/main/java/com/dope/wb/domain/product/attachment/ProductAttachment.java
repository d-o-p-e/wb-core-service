package com.dope.wb.domain.product.attachment;

import com.dope.wb.domain.product.Product;
import javax.persistence.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProductAttachment {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_IMAGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Long view;

    private String path;

    protected ProductAttachment() {
    }

    public ProductAttachment(Product product) {
        this.product = product;
        this.view = 0L;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void checkDir(String stringPath) {
        Path path = Paths.get(stringPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract Path createFilePath(Product product, MultipartFile file, String basePath);
}
