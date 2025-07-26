package com.example.service;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class EmployeeServiceRequest {

    private LocalDate birthStart;

    private LocalDate birthEnd;

}
