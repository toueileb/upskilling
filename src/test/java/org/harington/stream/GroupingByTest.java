package org.harington.stream;

import org.harington.stream.model.City;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GroupingByTest {

    // Création de quelques villes pour tester
    private static final List<City> CITIES = List.of(
            new City("New York", "New York", 8398748, 468.9),
            new City("Buffalo", "New York", 255284, 104.6),
            new City("Los Angeles", "California", 3990456, 1213.9),
            new City("Chicago", "Illinois", 2705994, 606.1),
            new City("Houston", "Texas", 2325502, 669.2),
            new City("Phoenix", "Arizona", 1660272, 517.6)
    );


    // Création de l'instance de GroupingBy à tester
    private static final GroupingBy groupingBy = new GroupingBy();

    @Test
    public void testGroupByState() {

        // Appel de la méthode à tester
        var groupedCities = groupingBy.groupByState(CITIES);

        // Vérification des résultats
        assertEquals(2, groupedCities.get("New York").size());
        assertEquals(1, groupedCities.get("California").size());
        assertEquals(1, groupedCities.get("Illinois").size());
        assertEquals(1, groupedCities.get("Texas").size());
        assertEquals(1, groupedCities.get("Arizona").size());
    }

    @Test
    public void testGetPopulationOfCitiesPerState() {

        var populationOfCitiesPerState = groupingBy.totalPopulationByState(CITIES);
        assertNotNull(populationOfCitiesPerState);
        assertEquals(8654032, populationOfCitiesPerState.get("New York"));
        assertEquals(3990456, populationOfCitiesPerState.get("California"));
        assertEquals(2705994, populationOfCitiesPerState.get("Illinois"));
        assertEquals(2325502, populationOfCitiesPerState.get("Texas"));
        assertEquals(1660272, populationOfCitiesPerState.get("Arizona"));
    }

    @Test
    public void testGetCountOfCitiesPerState() {
        var countOfCitiesPerState = groupingBy.countCitiesByState(CITIES);
        assertNotNull(countOfCitiesPerState);
        assertEquals(2, countOfCitiesPerState.get("New York"));
        assertEquals(1, countOfCitiesPerState.get("California"));
        assertEquals(1, countOfCitiesPerState.get("Illinois"));
        assertEquals(1, countOfCitiesPerState.get("Texas"));
        assertEquals(1, countOfCitiesPerState.get("Arizona"));
    }

    @Test
    public void testMostPopulousCityByState() {
        Map<String, City> mostedPopulousCityByState = groupingBy.mostPopulousCityByState(CITIES);
        assertNotNull(mostedPopulousCityByState);
        assertEquals("New York", mostedPopulousCityByState.get("New York").getName());
        assertEquals("Los Angeles", mostedPopulousCityByState.get("California").getName());
        assertEquals("Chicago", mostedPopulousCityByState.get("Illinois").getName());
        assertEquals("Houston", mostedPopulousCityByState.get("Texas").getName());
        assertEquals("Phoenix", mostedPopulousCityByState.get("Arizona").getName());

    }

    @Test
    public void testCitiesByPopulationDensity() {

        var citiesByPopulationDensity = groupingBy.citiesByPopulationDensity(CITIES);
        assertEquals(6, citiesByPopulationDensity.get("High").size());

    }


}
