package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.DeviceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
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