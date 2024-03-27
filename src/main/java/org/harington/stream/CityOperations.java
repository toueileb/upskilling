package org.harington.stream;

import org.harington.stream.model.City;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


public class CityOperations {

    //collect(toList())

    public List<String> extractCityNames(List<City> cities) {
        return cities.stream()
                .map(City::getName)
                .collect(Collectors.toList());
    }

    // collect(toSet())
    public Set<String> extractUniqueStates(List<City> cities) {
        return cities.stream()
                .map(City::getState)
                .collect(Collectors.toSet());
    }

    // collect(joining())
    public String concatCityNames(List<City> cities) {
        return cities.stream()
                .map(City::getName)
                .collect(Collectors.joining(", "));
    }

    // collect(toMap())
    public Map<String, Integer> cityPopulationMap(List<City> cities) {
        return cities.stream()
                .collect(Collectors.toMap(City::getName, City::getPopulation));
    }

    // This method groups cities by their state names and collects them into a Map,
// where the keys are state names and the values are lists of city names belonging to each state.
    public Map<String, List<String>> citiesByState(List<City> cities) {
        return cities.stream()
                .collect(Collectors.groupingBy(
                        City::getState, // Group cities by state name
                        Collectors.mapping(City::getName, Collectors.toList()) // Map city objects to city names and collect them into a list
                ));
    }

    /*
     * Lever une exception en cas de conflit : Cette approche permet de détecter et de signaler les doublons.
     * */
    public Map<String, City> cityMapDuplicatesWithException(List<City> cities) {
        return cities.stream()
                .collect(Collectors.toMap(City::getName, Function.identity(),
                        (existing, replacement) -> {
                            throw new IllegalStateException("Duplicate key found: " + existing.getName());
                        }));
    }

    /*
     * Conserver la valeur existante : Dans ce cas, vous conservez la valeur de la clé existante et ignorez la nouvelle valeur.
     * */
    public Map<String, City> cityMapMapDuplicatesKeepExistingValue(List<City> cities) {
        return cities.stream()
                .collect(Collectors.toMap(City::getName, Function.identity(),
                        (existing, replacement) -> existing));
    }


    /*
     * Remplacer par la nouvelle valeur : Vous remplacez la valeur existante par la nouvelle valeur.
     * */
    public Map<String, City> cityMapMapDuplicatesReplaceExistingValue(List<City> cities) {
        return cities.stream()
                .collect(Collectors.toMap(City::getName, Function.identity(),
                        (existing, replacement) -> replacement));
    }
}
