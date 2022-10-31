package com.aman.dto.response;

import com.aman.domain.entity.Department;
import com.aman.domain.entity.SubDepartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryStatistics {
    Department department;
    SubDepartment subDepartment;
    long min;
    long max;
    long mean;
    long totalElements;
}
