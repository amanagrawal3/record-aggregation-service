package com.aman.service;

import com.aman.domain.entity.Record;
import com.aman.dto.request.CreateRecord;
import com.aman.dto.request.RecordSearch;
import com.aman.dto.response.SummaryStatisticsResponse;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
public interface RecordService {

    public Record createRecord(CreateRecord recordDto);

    public void deleteRecord(String recordId);

    /**
     * This method get summary statistics for all record filtered by search criteria
     *
     * @param recordSearch
     * @return
     */
    public SummaryStatisticsResponse getSSTotal(RecordSearch recordSearch);

    public SummaryStatisticsResponse getSSByDepartment();

    public SummaryStatisticsResponse getSSByDepartmentAndSubDepartment();


}
