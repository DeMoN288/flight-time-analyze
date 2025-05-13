package com.company.flighttime.model;

public class CrewMember {
    private int id;
    private String name;

    public CrewMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}