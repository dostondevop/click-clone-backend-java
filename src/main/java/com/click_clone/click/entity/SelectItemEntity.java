package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private SelectItemEntity parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "selectItem")
    @JsonManagedReference
    @Builder.Default
    private List<ServiceSerialNumberEntity> serviceSerialNumbers = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(nullable = false)
    private InputEntity input;
}