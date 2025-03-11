package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;
import com.click_clone.click.entity.enums.DeviceType;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeviceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String operationSystemName;

    private String ipAddress;

    private DeviceType type;

    private String browserName;

    @ManyToOne
    private UserEntity user;
}