package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HomeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "home")
    @JsonManagedReference
    @Builder.Default
    private List<FavoriteEntity> favoriteList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}