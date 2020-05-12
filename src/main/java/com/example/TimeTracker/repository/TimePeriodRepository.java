package com.example.TimeTracker.repository;

import com.example.TimeTracker.model.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimePeriodRepository extends JpaRepository<TimePeriod, Long> {
}
