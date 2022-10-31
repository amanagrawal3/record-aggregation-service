package com.aman.dto.request;

import com.aman.exception.validators.ValueOfEnum;
import com.aman.domain.entity.Department;
import com.aman.domain.entity.SubDepartment;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateRecord {

    @NotEmpty(message = "name must be provided")
    private String name;

    @NotEmpty(message = "Salary must be provided in numbers")
    @Pattern(regexp = "^[1-9][0-9]*(\\.[0-9])?", message = "Invalid characters found in salary")
    private String salary;
    private String currency;
    @ValueOfEnum(enumClass = Department.class, message = "Please provide valid department")
    private String department;
    @ValueOfEnum(enumClass = SubDepartment.class, message = "Please provide valid sub department")
    private String subDepartment;
    private boolean onContract;
}
