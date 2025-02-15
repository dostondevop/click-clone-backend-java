package com.click_clone.click.contoller.user.convertor;

import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.entity.RoleEntity;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.entity.enums.UserRole;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserAuthConverter {
    @Autowired
    private RoleRepository roleRepository;

    public abstract UserEntity dtoToUser(UserCreateRequestDto requestDto);

    @AfterMapping
    protected void setUserRole(UserEntity user) {
        RoleEntity role = roleRepository.findByRole(UserRole.UNCOMPLETED)
                .orElseThrow(() -> new RecordNotFoundException("Role not found."));
        user.setRole(role);
    }
}