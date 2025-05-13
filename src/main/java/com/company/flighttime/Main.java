package com.company.flighttime;

import com.company.flighttime.model.CrewMember;
import com.company.flighttime.model.Flight;
import com.company.flighttime.model.FlightReport;
import com.company.flighttime.parser.InputDataParser;
import com.company.flighttime.service.FlightTimeCalculator;
import com.company.flighttime.util.JsonWriter;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -jar flight-time-analyzer.jar <input-file> <output-file>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        InputDataParser parser = new InputDataParser();
        List<CrewMember> crewMembers = parser.parseCrewMembers(inputFile);
        List<Flight> flights = parser.parseFlights(inputFile);

        FlightTimeCalculator calculator = new FlightTimeCalculator();
        List<FlightReport> reports = calculator.calculateReports(crewMembers, flights);

        JsonWriter.writeReportsToFile(reports, Paths.get(outputFile));
        System.out.println("Report generated: " + outputFile);
    }
}