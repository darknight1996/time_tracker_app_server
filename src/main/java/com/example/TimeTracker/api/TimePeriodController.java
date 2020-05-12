package com.example.TimeTracker.api;

import com.example.TimeTracker.dto.MonthWorkTimeDTO;
import com.example.TimeTracker.model.Employee;
import com.example.TimeTracker.model.TimePeriod;
import com.example.TimeTracker.service.EmployeeService;
import com.example.TimeTracker.service.TimePeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TimePeriodController {

    private TimePeriodService timePeriodService;

    public TimePeriodController(TimePeriodService timePeriodService) {
        this.timePeriodService = timePeriodService;
    }

    @PostMapping("/addTime/{id}")
    ResponseEntity<?> createTimePeriod(@RequestBody TimePeriod timePeriod, @PathVariable Long id) throws URISyntaxException {
        if (timePeriod.getStartDate().getTime() > timePeriod.getEndDate().getTime()) {
            return ResponseEntity.badRequest().build();
        }
        Employee employee = new Employee();
        employee.setId(id);
        timePeriod.setEmployee(employee);
        timePeriodService.createTimePeriod(timePeriod);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/times/{id}")
    List<TimePeriod> times(@PathVariable Long id) {
        return this.timePeriodService.getAllTimePeriods()
                .stream()
                .filter(timePeriod -> timePeriod.getEmployee().getId() == id)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/time/delete/{id}")
    ResponseEntity<?> deleteTimePeriod(@PathVariable Long id) {
        timePeriodService.deleteTimePeriodById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/times/year/{year}/{id}")
    List<MonthWorkTimeDTO> times(@PathVariable Long id, @PathVariable int year) {
        return this.timePeriodService.getYearWorkTimeReportByEmployeeId(id, year);
    }


}
