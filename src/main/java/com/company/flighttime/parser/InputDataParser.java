package com.company.flighttime.parser;

import com.company.flighttime.model.CrewMember;
import com.company.flighttime.model.Flight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InputDataParser {
    public List<CrewMember> parseCrewMembers(String filePath) throws Exception {
        List<CrewMember> crew = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean inCrew = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("CREW")) {
                    inCrew = true;
                    continue;
                }
                if (line.trim().equals("FLIGHTS")) break;
                if (inCrew && !line.trim().isEmpty()) {
                    String[] parts = line.split(";", 2);
                    crew.add(new CrewMember(Integer.parseInt(parts[0]), parts[1]));
                }
            }
        }
        return crew;
    }

    public List<Flight> parseFlights(String filePath) throws Exception {
        List<Flight> flights = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean inFlights = false;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("FLIGHTS")) {
                    inFlights = true;
                    continue;
                }
                if (inFlights && !line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    String flightNumber = parts[0];
                    String aircraftType = parts[1];
                    String aircraftNumber = parts[2];
                    LocalDateTime dep = LocalDateTime.parse(parts[3], dtf);
                    LocalDateTime arr = LocalDateTime.parse(parts[4], dtf);
                    String depAirport = parts[5];
                    String arrAirport = parts[6];
                    List<Integer> crewIds = new ArrayList<>();
                    for (String id : parts[7].split(",")) {
                        crewIds.add(Integer.parseInt(id));
                    }
                    flights.add(new Flight(flightNumber, aircraftType, aircraftNumber, dep, arr, depAirport, arrAirport, crewIds));
                }
            }
        }
        return flights;
    }
}