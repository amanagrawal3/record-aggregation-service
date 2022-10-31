package com.aman.service.impl;

import com.aman.domain.entity.Record;
import com.aman.service.AggregationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordAggregationServiceImpl implements AggregationService {

    /**
     * @param recordList
     * @return
     */
    @Override
    public Long max(List<Record> recordList) {
        long max = Long.MIN_VALUE;
        for (Record record : recordList) {
            max = Math.max(max, Long.parseLong(record.getSalary()));
        }
        return max;
    }

    /**
     * @param recordList
     * @return
     */
    @Override
    public Long min(List<Record> recordList) {
        long min = Long.MAX_VALUE;
        for (Record record : recordList) {
            min = Math.min(min, Long.parseLong(record.getSalary()));
        }
        return min;
    }

    /**
     * @param recordList
     * @return
     */
    @Override
    public Long mean(List<Record> recordList) {
        long totalSum = 0;
        for (Record record : recordList) {
            totalSum = totalSum + Long.parseLong(record.getSalary());
        }

        return totalSum / recordList.size();
    }
}
