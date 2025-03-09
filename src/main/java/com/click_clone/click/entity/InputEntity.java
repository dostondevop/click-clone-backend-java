package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.InputType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String label;

    @Column(nullable = false)
    private String placeholder;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InputType inputType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private List<SelectItemEntity> selectItems = new ArrayList<>();
}