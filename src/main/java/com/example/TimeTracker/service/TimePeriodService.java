package com.example.TimeTracker.service;

import com.example.TimeTracker.dto.MonthWorkTimeDTO;
import com.example.TimeTracker.model.TimePeriod;

import java.util.List;
import java.util.Optional;

public interface TimePeriodService {

    Optional<TimePeriod> getTimePeriodById(Long id);

    void deleteTimePeriodById(Long id);

    TimePeriod updateTimePeriod(TimePeriod timePeriod);

    TimePeriod createTimePeriod(TimePeriod timePeriod);

    List<TimePeriod> getAllTimePeriods();

    List<MonthWorkTimeDTO> getYearWorkTimeReportByEmployeeId(Long id, int year);
}
