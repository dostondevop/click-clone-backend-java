package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;
import com.click_clone.click.entity.enums.AttachmentType;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long size;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;
    @Column(nullable = false)
    private byte[] content;
}