package com.dope.wb.repository;

import com.dope.wb.domain.board.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
