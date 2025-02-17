package com.click_clone.click.service.util;

import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.enums.AttachmentType;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@UtilityClass
public class AttachmentUtil {

    public static AttachmentEntity buildAttachmentFromDefaultImage(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        File file = resource.getFile();
        byte[] fileBytes = Files.readAllBytes(file.toPath());

        long fileSize = file.length();

        return AttachmentEntity.builder()
                .name(resource.getFilename())
                .size(fileSize)
                .attachmentType(AttachmentType.IMAGE)
                .content(fileBytes)
                .build();
    }
}