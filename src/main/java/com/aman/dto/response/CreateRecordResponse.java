package com.aman.dto.response;

import com.aman.domain.entity.Department;
import com.aman.domain.entity.SubDepartment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRecordResponse {
    private long id;
    private String name;
    private String salary;
    private String currency;
    private Department department;
    private SubDepartment subDepartment;
}
