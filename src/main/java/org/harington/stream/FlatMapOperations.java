package org.harington.stream;

import org.harington.stream.model.City;
import org.harington.stream.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlatMapOperations {

    // Exercice d'âge moyen
    public double averageAgeOfPeople(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
    }

    // Exercice de filtrage
    public List<Person> filterPeopleAboveAge(List<City> cities, int ageThreshold) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .filter(person -> person.getAge() > ageThreshold)
                .collect(Collectors.toList());
    }

    // Exercice de regroupement
    public Map<Integer, List<Person>> groupPeopleByAge(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .collect(Collectors.groupingBy(Person::getAge));
    }

    // Exercice de comptage
    public long countTotalPeople(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .count();
    }

    // Exercice de tri
    public List<Person> sortPeopleByAge(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
    }

}
