package com.dope.wb.service.board;

import com.dope.wb.domain.board.attachment.Attachment;
import com.dope.wb.domain.board.attachment.LibraryAttachment;
import com.dope.wb.domain.board.attachment.ProductImage;
import com.dope.wb.domain.board.library.Library;
import com.dope.wb.dto.LibraryCreateRequestDto;
import com.dope.wb.dto.LibraryDetailResponseDto;
import com.dope.wb.repository.LibraryAttachmentRepository;
import com.dope.wb.repository.LibraryRepository;
import com.dope.wb.specification.LibraryCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryAttachmentRepository libraryAttachmentRepository;

    @Value("${app.resource.library.attachment}")
    private String libraryAttachmentBaseDir;

    @Transactional
    @Override
    public void create(LibraryCreateRequestDto libraryCreateRequestDto) {
        Library library = Library.builder()
                .title(libraryCreateRequestDto.getTitle())
                .content(libraryCreateRequestDto.getContent())
                .category(libraryCreateRequestDto.getCategory())
                .build();
        libraryRepository.save(library);

        if(libraryCreateRequestDto.getAttachments() != null) {
            uploadLibraryAttachments(library, libraryCreateRequestDto.getAttachments());
        }
    }

    private void uploadLibraryAttachments(Library library, List<MultipartFile> attachments) {
        for (MultipartFile attachment : attachments) {
            LibraryAttachment libraryAttachment = new LibraryAttachment(library);
            libraryAttachmentRepository.save(libraryAttachment);

            Path filePath = libraryAttachment.createFilePath(library.getId().toString(), attachment, libraryAttachmentBaseDir);
            libraryAttachment.setPath(filePath.toString());

            try {
                Files.copy(attachment.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public LibraryDetailResponseDto readDetail(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found"));
        library.increaseView();
        List<LibraryAttachment> attachments = libraryAttachmentRepository.findAllByLibrary(library);
        List<String> attachmentPaths = attachments.stream().map(LibraryAttachment::getPath).collect(Collectors.toList());
        return LibraryDetailResponseDto.builder()
                .id(library.getId())
                .title(library.getTitle())
                .content(library.getContent())
                .view(library.getView())
                .attachmentList(attachmentPaths)
                .build();
    }

    @Override
    public void update(String serial) {

    }

    @Override
    public void delete(String serial) {

    }

    @Override
    public Page<LibraryDetailResponseDto> readLibraryList(Pageable pageable) {
        return null;
    }
}
