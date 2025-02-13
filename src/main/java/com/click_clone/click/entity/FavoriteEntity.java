package com.click_clone.click.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FavoriteEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String data;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @Column(nullable = false)
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private UserEntity user;

    @ManyToOne
    @JsonBackReference
    private HomeEntity home;
}