package org.harington.stream;

import org.harington.stream.model.City;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupingBy {

    // Grouper les villes par état
    public Map<String, List<City>> groupByState(List<City> cities){
        return cities.stream().collect(Collectors.groupingBy(City::getState));
    }

    // Somme de la population par état
    public Map<String, Integer> totalPopulationByState(List<City> cities){
        return cities.stream()
                .collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
    }

    // Nombre de villes par état
    public Map<String, Long> countCitiesByState(List<City> cities){
        return cities.stream()
                .collect(Collectors.groupingBy(City::getState, Collectors.counting()));
    }

    // Villes les plus peuplées par état
    public Map<String, City> mostPopulousCityByState(List<City> cities) {
        return cities.stream()
                .collect(Collectors.groupingBy(City::getState,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(City::getPopulation)),
                                Optional::get)));
    }

    // Villes par densité de population
    public Map<String, List<City>> citiesByPopulationDensity(List<City> cities) {
        return cities.stream()
                .collect(Collectors.groupingBy(city -> {
                    double density = city.getPopulation() / city.getArea();
                    if (density < 100) return "Low";
                    else if (density < 500) return "Medium";
                    else return "High";
                }));
    }

    // Villes les plus grandes par état
    public Map<String, City> largestCityByState(List<City> cities) {
        return cities.stream()
                .collect(Collectors.groupingBy(City::getState,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(City::getArea)),
                                Optional::get)));
    }


}
