package com.aman.service;

import com.aman.domain.entity.Record;

import java.util.List;

public interface AggregationService {

    public Long max(List<Record> recordList);
    public Long min(List<Record> recordList);
    public Long mean(List<Record> recordList);


}
