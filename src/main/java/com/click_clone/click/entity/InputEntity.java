package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.InputType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String placeholder;

    @Column(nullable = false)
    private InputType inputType;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SelectItemEntity> selectItems = new ArrayList<>();
}