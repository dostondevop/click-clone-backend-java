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
public class PosterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder.Default
    private boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "news_viewers",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    private List<UserEntity> viewers = new ArrayList<>();;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "news_likes",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    private List<UserEntity> likedPeople = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private AttachmentEntity imageAttachment;
}