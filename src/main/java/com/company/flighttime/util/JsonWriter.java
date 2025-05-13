package com.company.flighttime.util;

import com.company.flighttime.model.FlightReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonWriter {
    public static void writeReportsToFile(List<FlightReport> reports, Path file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(file.toFile(), reports);
    }
}