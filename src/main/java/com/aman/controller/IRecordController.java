package com.aman.controller;

import com.aman.dto.request.CreateRecord;
import com.aman.dto.response.CreateRecordResponse;
import com.aman.dto.response.SummaryStatisticsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

/*
 * Demonstrates how to set up Restful API endpoints using Spring MVC
 */

@RequestMapping(value = "/v1/records")
public interface IRecordController {

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRecordResponse createRecord(@RequestBody @Valid CreateRecord record);

    @RequestMapping(value = "{recordId}",
            method = RequestMethod.DELETE,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecord(@PathVariable String recordId);

    @RequestMapping(value = "/summary/dept",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public SummaryStatisticsResponse getSSByDepartment();

    @RequestMapping(value = "/summary/deptSubdept",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public SummaryStatisticsResponse getSSByDeptAndSubDept();

    @RequestMapping(value = "/summary/contract/{contract}",
            method = RequestMethod.GET,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public SummaryStatisticsResponse getSSByContract(@PathVariable boolean contract);

    @RequestMapping(value = "/summary",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public SummaryStatisticsResponse getSSTotal();

}
