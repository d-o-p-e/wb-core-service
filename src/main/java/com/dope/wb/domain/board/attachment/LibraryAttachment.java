package com.dope.wb.domain.board.attachment;

import com.dope.wb.domain.board.product.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import java.nio.file.Path;

@Entity
public class LibraryAttachment extends Attachment{
    @Override
    public Path createFilePath(Product product, MultipartFile file, String basePath) {
        return null;
    }
}
