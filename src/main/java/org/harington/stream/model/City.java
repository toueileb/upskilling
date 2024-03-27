package org.harington.stream.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class City {

    private String name;
    private String state;
    private int population;
    private double area;
    private List<Person> people = List.of();

    public City(String name, String state, int population, double area) {
        this.name = name;
        this.state = state;
        this.population = population;
        this.area = area;
    }

    public City(String name, String state, int population, double area, List<Person> people) {
        this.name = name;
        this.state = state;
        this.population = population;
        this.area = area;
        this.people = people;
    }
}
