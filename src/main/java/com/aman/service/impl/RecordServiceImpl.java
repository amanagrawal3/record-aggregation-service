package com.aman.service.impl;

import com.aman.domain.entity.Department;
import com.aman.domain.entity.Record;
import com.aman.domain.entity.SubDepartment;
import com.aman.dto.request.CreateRecord;
import com.aman.dto.request.RecordSearch;
import com.aman.dto.response.SummaryStatistics;
import com.aman.dto.response.SummaryStatisticsResponse;
import com.aman.exception.ApplicationException;
import com.aman.exception.DuplicateRecordException;
import com.aman.exception.ErrorInfo;
import com.aman.exception.RecordNotFoundException;
import com.aman.repository.RecordRepository;
import com.aman.service.AggregationService;
import com.aman.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private static final Logger log = LoggerFactory.getLogger(RecordService.class);
    private final RecordRepository recordRepository;

    private final AggregationService aggregations;

    /**
     * This method create a new record in db
     *
     * @param recordDto
     * @return
     */
    public Record createRecord(CreateRecord recordDto) {
        Record record = Record.builder()
                .department(Department.valueOf(recordDto.getDepartment()))
                .subDepartment(SubDepartment.valueOf(recordDto.getSubDepartment()))
                .salary(recordDto.getSalary())
                .name(recordDto.getName())
                .build();

        recordRepository.findByName(recordDto.getName()).ifPresent(record1 -> {
            throw new DuplicateRecordException(ErrorInfo.DUPLICATE_RECORD_ERROR);
        });

        try {
            return recordRepository.save(record);
        } catch (Exception exception) {
            log.error("Error occured while creating record", exception);
            throw new ApplicationException(ErrorInfo.GENERIC_ERROR);
        }
    }

    @Override
    public void deleteRecord(String recordId) {
        recordRepository.findById(Long.valueOf(recordId))
                .orElseThrow(() -> new RecordNotFoundException(ErrorInfo.RECORD_NOTFOUND_ERROR));
    }

    /**
     * This method get summary statistics for all record filtered by search criteria
     *
     * @param recordSearch
     * @return
     */
    public SummaryStatisticsResponse getSSTotal(RecordSearch recordSearch) {
        List<Record> records = this.getRecords(recordSearch);
        //Run for each separate department

        SummaryStatistics summaryStatistics = SummaryStatistics.builder()
                .department(Department.ALL)
                .subDepartment(SubDepartment.ALL)
                .totalElements(records.size())
                .min(aggregations.min(records))
                .max(aggregations.max(records))
                .mean(aggregations.mean(records))
                .build();
        return SummaryStatisticsResponse.builder()
                .summaryStatistics(Collections.singletonList(summaryStatistics))
                .build();
    }


    /**
     * This method get summary statistic for all records group by department
     *
     * @return
     */
    public SummaryStatisticsResponse getSSByDepartment() {
        List<Record> records = this.getRecords(null);
        Map<Department, Map<SubDepartment, List<Record>>> departmentSubDeptMap = getDeptSubDeptGroupMap(records);

        List<SummaryStatistics> summaryStatisticsList = new ArrayList<>();
        //Run for each separate department
        for (Map.Entry<Department, Map<SubDepartment, List<Record>>> entry : departmentSubDeptMap.entrySet()) {
            //collect all records of same department and compute statistics
            List<Record> recordList = entry.getValue().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            SummaryStatistics summaryStatistics = SummaryStatistics.builder()
                    .department(entry.getKey())
                    .subDepartment(SubDepartment.ALL)
                    .totalElements(recordList.size())
                    .min(aggregations.min(recordList))
                    .max(aggregations.max(recordList))
                    .mean(aggregations.mean(recordList))
                    .build();
            summaryStatisticsList.add(summaryStatistics);

        }

        return SummaryStatisticsResponse.builder()
                .summaryStatistics(summaryStatisticsList)
                .build();
    }

    /**
     * This method get summary statistic for all records group by department and subdepartment
     *
     * @return
     */
    public SummaryStatisticsResponse getSSByDepartmentAndSubDepartment() {
        List<Record> records = this.getRecords(null);
        Map<Department, Map<SubDepartment, List<Record>>> departmentSubDeptMap = getDeptSubDeptGroupMap(records);

        List<SummaryStatistics> summaryStatisticsList = new ArrayList<>();
        //Run for each separate department
        for (Map.Entry<Department, Map<SubDepartment, List<Record>>> entry : departmentSubDeptMap.entrySet()) {
            //Collect records per sub-department and compute results
            for (Map.Entry<SubDepartment, List<Record>> subDepartmentListEntry : entry.getValue().entrySet()) {
                SummaryStatistics summaryStatistics = SummaryStatistics.builder()
                        .department(entry.getKey())
                        .subDepartment(subDepartmentListEntry.getKey())
                        .totalElements(subDepartmentListEntry.getValue().size())
                        .min(aggregations.min(subDepartmentListEntry.getValue()))
                        .max(aggregations.max(subDepartmentListEntry.getValue()))
                        .mean(aggregations.mean(subDepartmentListEntry.getValue()))
                        .build();
                summaryStatisticsList.add(summaryStatistics);
            }

        }
        return SummaryStatisticsResponse.builder()
                .summaryStatistics(summaryStatisticsList)
                .build();
    }


    /**
     * This method fetch all records from db by search criteria
     *
     * @param recordSearch
     * @return
     */
    private List<Record> getRecords(RecordSearch recordSearch) {
        try {
            return recordRepository.findAll();
        } catch (Exception e) {
            log.error("Error occured while fetching record", e);
            throw new ApplicationException(ErrorInfo.GENERIC_ERROR);
        }
    }


    /**
     * This method separate the records by department and subdepartment
     *
     * @param records
     * @return
     */
    private Map<Department, Map<SubDepartment, List<Record>>> getDeptSubDeptGroupMap(List<Record> records) {
        Map<Department, Map<SubDepartment, List<Record>>> recordMap = records.stream()
                .collect(Collectors.groupingBy(Record::getDepartment,
                        Collectors.groupingBy(Record::getSubDepartment)
                ));
        return recordMap;
    }
}
