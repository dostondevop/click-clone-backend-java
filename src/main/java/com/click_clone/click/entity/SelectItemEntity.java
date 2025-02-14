package com.click_clone.click.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "selectItem", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceSerialNumberEntity> serviceSerialNumbers = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(nullable = false)
    private InputEntity input;
}