package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.entity.InputValue;
import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.repository.InputRepository;
import com.click_clone.click.repository.InputValueRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputService {
    private final InputRepository inputRepository;
    private final InputValueRepository inputValueRepository;

    public List<InputValue> getInputValues(List<InputEntity> inputEntities) {
        return inputEntities.stream()
                .map(input -> inputValueRepository.findByInput_Id(input.getId()))
                .collect(Collectors.toList());
    }

    public List<InputValue> replaceInputIdInInputValueWithInput(List<InputValue> inputValues) {
        return inputValues.stream()
                .peek(inputValue -> inputRepository.findById(inputValue.getInput().getId())
                        .ifPresent(inputValue::setInput))
                .collect(Collectors.toList());
    }
}