package com.example.TimeTracker.service.impl;

import com.example.TimeTracker.dto.MonthWorkTimeDTO;
import com.example.TimeTracker.model.Employee;
import com.example.TimeTracker.model.TimePeriod;
import com.example.TimeTracker.repository.EmployeeRepository;
import com.example.TimeTracker.repository.TimePeriodRepository;
import com.example.TimeTracker.service.TimePeriodService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component("TimePeriodServiceImpl")
public class TimePeriodServiceImpl implements TimePeriodService {

    private int MONTH_WORK_HOURS = 160;

    private TimePeriodRepository timePeriodRepository;

    public TimePeriodServiceImpl(TimePeriodRepository timePeriodRepository) {
        this.timePeriodRepository = timePeriodRepository;
    }

    public Optional<TimePeriod> getTimePeriodById(Long id) {
        return timePeriodRepository.findById(id);
    }

    public void deleteTimePeriodById(Long id) {
        timePeriodRepository.deleteById(id);
    }

    public TimePeriod updateTimePeriod(TimePeriod timePeriod) {
        return timePeriodRepository.save(timePeriod);
    }

    public TimePeriod createTimePeriod(TimePeriod timePeriod) {
        return timePeriodRepository.save(timePeriod);
    }

    public List<TimePeriod> getAllTimePeriods() {
        return timePeriodRepository.findAll();
    }

    public List<MonthWorkTimeDTO> getYearWorkTimeReportByEmployeeId(Long id, int year) {
        List<TimePeriod> timePeriods = timePeriodRepository.findAll().stream().filter(timePeriod -> {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(timePeriod.getStartDate());
            return ((calendar.get(Calendar.YEAR) == year) && (timePeriod.getEmployee().getId() == id));
        }).collect(Collectors.toList());

        Map<Integer, String> monthsMap = new HashMap<>();
        monthsMap.put(0, "January");
        monthsMap.put(1, "February");
        monthsMap.put(2, "March");
        monthsMap.put(3, "April");
        monthsMap.put(4, "May");
        monthsMap.put(5, "June");
        monthsMap.put(6, "July");
        monthsMap.put(7, "August");
        monthsMap.put(8, "September");
        monthsMap.put(9, "October");
        monthsMap.put(10, "November");
        monthsMap.put(11, "December");

        List<MonthWorkTimeDTO> result = new ArrayList<>(11);

        monthsMap.forEach((monthNumber, monthName) -> {
            MonthWorkTimeDTO dto = new MonthWorkTimeDTO(monthName, 0, 0);
            for (TimePeriod timePeriod : timePeriods) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(timePeriod.getStartDate());
                if (calendar.get(Calendar.MONTH) == monthNumber) {
                    double hours = (timePeriod.getEndDate().getTime() - timePeriod.getStartDate().getTime()) / (60 * 60 * 1000);
                    dto.setHours(dto.getHours() + hours);
                }
            }
            //calculate percent of wor time in current month
            dto.setPercent((int) ((dto.getHours() * 100) / MONTH_WORK_HOURS));
            result.add(dto);
        });

        return result;
    }


}
