package com.dope.wb.domain.board.attachment;

import javax.persistence.*;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Attachment {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_IMAGE_ID")
    private Long id;

    private Long view = 0L;

    private String path;

    protected Attachment() {
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

    public String getExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if(!filename.contains(".")) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invalid extension");
        }
        return filename.substring(filename.indexOf(".")).toLowerCase(Locale.ROOT);
    }

    abstract public Path combinePath(String basePath, String specification, String extension);

    abstract public void validateSupportedExtension(String extension);

    public Path createFilePath(String specification, MultipartFile file, String basePath) {
        checkDir(basePath);
        String extension = getExtension(file);
        validateSupportedExtension(extension);
        return combinePath(basePath, specification, extension);
    }
}
