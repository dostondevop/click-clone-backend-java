package com.click_clone.click.service;

import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public ResponseEntity<?> getAttachmentContent(UUID id) {
        AttachmentEntity attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Attachment not found."));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(attachment.getContent());
    }
}