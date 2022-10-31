package com.aman.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummaryStatisticsResponse {
    private List<SummaryStatistics> summaryStatistics;
}
