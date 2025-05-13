package com.company.flighttime.model;

import java.util.List;

public class FlightReport {
    public static class MonthInfo {
        public String month;
        public double totalFlightHours;
        public boolean over80Hours;
        public boolean over36HoursWeek;
        public boolean over8HoursDay;

        public MonthInfo(String month, double totalFlightHours, boolean over80Hours, boolean over36HoursWeek, boolean over8HoursDay) {
            this.month = month;
            this.totalFlightHours = totalFlightHours;
            this.over80Hours = over80Hours;
            this.over36HoursWeek = over36HoursWeek;
            this.over8HoursDay = over8HoursDay;
        }
    }

    public int crewMemberId;
    public String name;
    public List<MonthInfo> months;

    public FlightReport(int crewMemberId, String name, List<MonthInfo> months) {
        this.crewMemberId = crewMemberId;
        this.name = name;
        this.months = months;
    }
}