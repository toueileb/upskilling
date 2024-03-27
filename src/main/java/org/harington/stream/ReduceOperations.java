package org.harington.stream;

import org.harington.stream.model.City;
import org.harington.stream.model.Person;

import java.util.List;
import java.util.Optional;

public class ReduceOperations {

    // Calculez la somme des âges de toutes les personnes dans une liste de villes
    public int sumOfAllAges(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .mapToInt(Person::getAge)
                .reduce(0, Integer::sum);
    }

    // rouvez la personne la plus âgée dans une liste de villes
    public Optional<Person> findOldestPerson(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .reduce((person1, person2) -> person1.getAge() > person2.getAge() ? person1 : person2);
    }

    // Concaténez les noms de toutes les personnes dans une liste de villes
    public String concatenateAllNames(List<City> cities) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .map(Person::getName)
                .reduce((name1, name2) -> name1 + " " + name2)  // Ajouter un espace entre les noms
                .orElse("");  // Gérer le cas où il n'y a aucun nom
    }

    // Calculer la population totale de toutes les villes dans une liste
    public int totalPopulation(List<City> cities) {
        return cities.stream()
                .mapToInt(City::getPopulation)
                .reduce(0, Integer::sum);
    }

    // Vérifiez si au moins une personne a un âge supérieur à un seuil donné dans une liste de villes
    public boolean isAnyPersonAboveAgeThreshold(List<City> cities, int ageThreshold) {
        return cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .anyMatch(person -> person.getAge() > ageThreshold);
    }






}
