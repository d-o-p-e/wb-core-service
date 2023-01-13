package com.dope.wb.repository;

import com.dope.wb.domain.board.attachment.LibraryAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryAttachmentRepository extends JpaRepository<LibraryAttachment, Integer> {
}
