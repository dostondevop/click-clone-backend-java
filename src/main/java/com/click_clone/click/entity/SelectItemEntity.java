package com.click_clone.click.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class SelectItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private SelectItemEntity parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "selectItem",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private List<ServiceSerialNumberEntity> serviceSerialNumbers = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(nullable = false)
    private InputEntity input;
}