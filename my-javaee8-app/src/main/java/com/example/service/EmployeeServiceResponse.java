package com.example.service;

import com.example.db.entity.EmployeesEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EmployeeServiceResponse {

    /**
     * 結果コード
     */
    private String resultCode;

    private List<EmployeesEntity> employees;

}
