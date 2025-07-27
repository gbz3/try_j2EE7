package com.example.service;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@ToString
public class EmployeeServiceRequest {

    private LocalDate birthStart;

    private LocalDate birthEnd;

    private List<Integer> managerIds;

    private String firstName;

}
