package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.AttachmentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class AttachmentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long size;
    @Column(nullable = false)
    private AttachmentType attachmentType;
    @Column(nullable = false)
    private byte[] content;
}