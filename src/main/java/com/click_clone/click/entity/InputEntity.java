package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;
import com.click_clone.click.entity.enums.InputType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<SelectItemEntity> selectItems = new ArrayList<>();
}