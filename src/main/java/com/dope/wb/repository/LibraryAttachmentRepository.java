package com.dope.wb.repository;

import com.dope.wb.domain.board.attachment.LibraryAttachment;
import com.dope.wb.domain.board.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryAttachmentRepository extends JpaRepository<LibraryAttachment, Long> {
    List<LibraryAttachment> findAllByLibrary(Library library);
}
