package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String icon;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_parent",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    @Builder.Default
    private List<CategoryEntity> parentList = new ArrayList<>();
}