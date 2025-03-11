package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.service.util.MessageUtil;
import com.click_clone.click.repository.AttachmentRepository;
import com.click_clone.click.exception.RecordNotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public ResponseEntity<?> getAttachmentContent(UUID id) {
        AttachmentEntity attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.ATTACHMENT_NOT_FOUND_ERROR));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(attachment.getContent());
    }
}