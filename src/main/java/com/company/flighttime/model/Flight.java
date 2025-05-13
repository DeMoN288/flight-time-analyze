package com.company.flighttime.model;

import java.time.LocalDateTime;
import java.util.List;

public class Flight {
    private String flightNumber;
    private String aircraftType;
    private String aircraftNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureAirport;
    private String arrivalAirport;
    private List<Integer> crewIds;

    public Flight(String flightNumber, String aircraftType, String aircraftNumber,
                  LocalDateTime departureTime, LocalDateTime arrivalTime,
                  String departureAirport, String arrivalAirport, List<Integer> crewIds) {
        this.flightNumber = flightNumber;
        this.aircraftType = aircraftType;
        this.aircraftNumber = aircraftNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.crewIds = crewIds;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getAircraftType() { return aircraftType; }
    public String getAircraftNumber() { return aircraftNumber; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public String getDepartureAirport() { return departureAirport; }
    public String getArrivalAirport() { return arrivalAirport; }
    public List<Integer> getCrewIds() { return crewIds; }
}