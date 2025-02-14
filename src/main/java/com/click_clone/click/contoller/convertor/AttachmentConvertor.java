package com.click_clone.click.contoller.convertor;

import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.enums.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class AttachmentConvertor {

    public static AttachmentEntity convertToEntity(MultipartFile file) throws IOException {
        return AttachmentEntity.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .attachmentType(AttachmentType.IMAGE)
                .content(file.getBytes())
                .build();
    }
}
