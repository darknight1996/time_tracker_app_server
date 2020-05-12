package com.example.TimeTracker.dto;

public class MonthWorkTimeDTO {

    private String monthName;

    private int percent;

    private double hours;

    public MonthWorkTimeDTO() {
    }

    public MonthWorkTimeDTO(String monthName, int percent, double hours) {
        this.monthName = monthName;
        this.percent = percent;
        this.hours = hours;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
