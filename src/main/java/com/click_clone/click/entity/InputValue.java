package com.click_clone.click.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InputValue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "input_id")
    private InputEntity input;

    private boolean accountNumber;
}