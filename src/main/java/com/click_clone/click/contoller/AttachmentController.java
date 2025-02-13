package com.click_clone.click.contoller;

import com.click_clone.click.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/{attachmentId}")
    public ResponseEntity<?> getAttachmentContent(@PathVariable UUID attachmentId) {
        return attachmentService.getAttachmentContent(attachmentId);
    }
}
