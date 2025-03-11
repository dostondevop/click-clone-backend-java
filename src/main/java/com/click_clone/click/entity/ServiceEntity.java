package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private double commission = 0;
    private double cashback = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<InputEntity> inputs = new ArrayList<>();

    @ManyToOne
    private AttachmentEntity imageAttachment;

    @ManyToOne
    private CategoryEntity categoryEntity;
}