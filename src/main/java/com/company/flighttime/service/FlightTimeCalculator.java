package com.company.flighttime.service;

import com.company.flighttime.model.CrewMember;
import com.company.flighttime.model.Flight;
import com.company.flighttime.model.FlightReport;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class FlightTimeCalculator {
    public List<FlightReport> calculateReports(List<CrewMember> crewMembers, List<Flight> flights) {
        Map<Integer, List<Flight>> flightsByCrew = new HashMap<>();
        for (Flight flight : flights) {
            for (int crewId : flight.getCrewIds()) {
                flightsByCrew.computeIfAbsent(crewId, k -> new ArrayList<>()).add(flight);
            }
        }

        List<FlightReport> reports = new ArrayList<>();
        for (CrewMember crew : crewMembers) {
            List<Flight> memberFlights = flightsByCrew.getOrDefault(crew.getId(), Collections.emptyList());
            Map<String, List<Flight>> flightsByMonth = memberFlights.stream()
                    .collect(Collectors.groupingBy(f -> f.getDepartureTime().getYear() + "-" +
                            String.format("%02d", f.getDepartureTime().getMonthValue())));

            List<FlightReport.MonthInfo> months = new ArrayList<>();
            for (String month : flightsByMonth.keySet()) {
                List<Flight> monthFlights = flightsByMonth.get(month);

                // Суммарное время за месяц
                double totalHours = monthFlights.stream()
                        .mapToDouble(f -> Duration.between(f.getDepartureTime(), f.getArrivalTime()).toMinutes() / 60.0)
                        .sum();

                // Проверка по неделям
                Map<Integer, Double> weekHours = new HashMap<>();
                WeekFields weekFields = WeekFields.ISO;
                for (Flight f : monthFlights) {
                    LocalDate date = f.getDepartureTime().toLocalDate();
                    int week = date.get(weekFields.weekOfWeekBasedYear());
                    double hours = Duration.between(f.getDepartureTime(), f.getArrivalTime()).toMinutes() / 60.0;
                    weekHours.put(week, weekHours.getOrDefault(week, 0.0) + hours);
                }
                boolean over36Week = weekHours.values().stream().anyMatch(h -> h > 36);

                // Проверка по дням
                Map<LocalDate, Double> dayHours = new HashMap<>();
                for (Flight f : monthFlights) {
                    LocalDate date = f.getDepartureTime().toLocalDate();
                    double hours = Duration.between(f.getDepartureTime(), f.getArrivalTime()).toMinutes() / 60.0;
                    dayHours.put(date, dayHours.getOrDefault(date, 0.0) + hours);
                }
                boolean over8Day = dayHours.values().stream().anyMatch(h -> h > 8);

                months.add(new FlightReport.MonthInfo(
                        month,
                        totalHours,
                        totalHours > 80,
                        over36Week,
                        over8Day
                ));
            }
            reports.add(new FlightReport(crew.getId(), crew.getName(), months));
        }
        return reports;
    }
}