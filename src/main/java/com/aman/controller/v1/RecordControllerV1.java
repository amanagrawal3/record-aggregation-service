package com.aman.controller.v1;

import com.aman.controller.IRecordController;
import com.aman.dto.request.CreateRecord;
import com.aman.dto.request.RecordSearch;
import com.aman.dto.response.CreateRecordResponse;
import com.aman.dto.response.SummaryStatisticsResponse;
import com.aman.domain.entity.Record;
import com.aman.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RecordControllerV1 implements IRecordController {

    @Autowired
    private final RecordService recordService;

    public CreateRecordResponse createRecord(CreateRecord record) {
        Record result = recordService.createRecord(record);
        return CreateRecordResponse.builder().currency(result.getCurrency())
                .id(result.getId())
                .salary(result.getSalary())
                .name(result.getName())
                .department(result.getDepartment())
                .subDepartment(result.getSubDepartment())
                .build();
    }

    @Override
    public void deleteRecord(String recordId) {
        recordService.deleteRecord(recordId);
    }

    public SummaryStatisticsResponse getSSByDepartment() {
        return recordService.getSSByDepartment();

    }

    public SummaryStatisticsResponse getSSByDeptAndSubDept() {
        return recordService.getSSByDepartmentAndSubDepartment();

    }

    public SummaryStatisticsResponse getSSByContract(boolean contract) {
        return recordService.getSSTotal(RecordSearch.builder().contract(contract).build());
    }

    public SummaryStatisticsResponse getSSTotal() {
        return recordService.getSSTotal(null);
    }
}
