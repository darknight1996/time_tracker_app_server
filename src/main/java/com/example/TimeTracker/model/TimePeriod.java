package com.example.TimeTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TimePeriod")
public class TimePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    private Date startDate;

    private Date endDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;

    public TimePeriod() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
