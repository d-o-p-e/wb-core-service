package com.dope.wb.domain.board.attachment;

import com.dope.wb.domain.board.library.Library;
import lombok.Getter;

import javax.persistence.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Getter
@Entity
public class LibraryAttachment extends Attachment{

    static final List<String> validExtension = null;

    @Id
    @Column(name = "LIBRARY_ATTACHMENT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;

    protected LibraryAttachment() {
    }

    public LibraryAttachment(Library library) {
        this.library = library;
    }

    @Override
    public Path combinePath(String basePath, String specification, String extension) {
        return Paths.get(basePath + File.separator + specification + this.getId() + extension);
    }

    @Override
    public void validateSupportedExtension(String extension) {
    }

}
