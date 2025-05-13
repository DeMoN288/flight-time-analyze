package com.company.flighttime.service;

import com.company.flighttime.model.CrewMember;
import com.company.flighttime.model.Flight;
import com.company.flighttime.model.FlightReport;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightTimeCalculatorTest {
    @Test
    void testCalculateReports() {
        CrewMember crew = new CrewMember(1, "Test Pilot");
        Flight f1 = new Flight("SU1", "A320", "RA-1",
                LocalDateTime.of(2021, 1, 2, 8, 0),
                LocalDateTime.of(2021, 1, 2, 12, 0),
                "SVO", "LED", Arrays.asList(1));
        Flight f2 = new Flight("SU2", "A320", "RA-1",
                LocalDateTime.of(2021, 1, 3, 8, 0),
                LocalDateTime.of(2021, 1, 3, 12, 0),
                "LED", "SVO", Arrays.asList(1));
        FlightTimeCalculator calc = new FlightTimeCalculator();
        List<FlightReport> reports = calc.calculateReports(Arrays.asList(crew), Arrays.asList(f1, f2));
        assertEquals(1, reports.size());
        FlightReport.MonthInfo month = reports.get(0).months.get(0);
        assertEquals("2021-01", month.month);
        assertEquals(8.0, month.totalFlightHours, 0.01);
        assertFalse(month.over80Hours);
        assertFalse(month.over36HoursWeek);
        assertFalse(month.over8HoursDay);
    }
}