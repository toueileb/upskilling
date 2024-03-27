package org.harington.stream;

import org.harington.stream.model.City;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CityOperationsTest {

    @Test
    public void testExtractCityNames() {

        var cities = List.of(
                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234),
                new City("Houston", "TX", 2323000, 599.6)
        );
        var cityOperations = new CityOperations();

        List<String> cityNames = cityOperations.extractCityNames(cities);

        assertEquals(4, cityNames.size());
        assertTrue(cityNames.contains("New York"));
        assertTrue(cityNames.contains("Los Angeles"));
        assertTrue(cityNames.contains("Chicago"));
        assertTrue(cityNames.contains("Houston"));
    }

    @Test
    public void testExtractUniqueStates() {

        var cities = List.of(

                new City("New York", "NY", 8623000, 468.9),
                new City("Miami", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234),
                new City("Houston", "TX", 2323000, 599.6),
                new City("Chicago", "TX", 2323000, 599.6),
                new City("Miami", "FL", 4679635, 143.1),
                new City("Philadelphia", "PA", 1584064, 134.2)
        );

        var cityOperations = new CityOperations();

        Set<String> uniqueStates = cityOperations.extractUniqueStates(cities);
        assertEquals(6, uniqueStates.size());
        assertTrue(uniqueStates.contains("NY"));
        assertTrue(uniqueStates.contains("CA"));
        assertTrue(uniqueStates.contains("IL"));
        assertTrue(uniqueStates.contains("TX"));
        assertTrue(uniqueStates.contains("FL"));
        assertTrue(uniqueStates.contains("PA"));
    }

    @Test
    public void testConcatCityNames() {

        var cities = List.of(
                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234)
        );

        var cityOperations = new CityOperations();

        String concatenatedNames = cityOperations.concatCityNames(cities);
        assertEquals("New York, Los Angeles, Chicago", concatenatedNames);
    }

    @Test
    public void testCityPopulationMap() {
        var cities = List.of(

                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234)
        );

        var cityOperations = new CityOperations();

        var populationMap = cityOperations.cityPopulationMap(cities);

        assertEquals(3, populationMap.size());
        assertEquals(8623000, populationMap.get("New York"));
        assertEquals(3994000, populationMap.get("Los Angeles"));
        assertEquals(2716000, populationMap.get("Chicago"));
    }

    @Test
    public void testCitiesByState() {

        var cities = List.of(
                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234),
                new City("San Francisco", "CA", 883305, 231.89),
                new City("Albany", "NY", 96460, 21.4)
        );

        var cityOperations = new CityOperations();

        var citiesByStateMap = cityOperations.citiesByState(cities);

        assertEquals(3, citiesByStateMap.size());
        assertEquals(List.of("New York", "Albany"), citiesByStateMap.get("NY"));
        assertEquals(List.of("Los Angeles", "San Francisco"), citiesByStateMap.get("CA"));
        assertEquals(List.of("Chicago"), citiesByStateMap.get("IL"));
    }

    @Test
    public void testCityMapDuplicatesWithException() {

        var cities = List.of(
                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234),
                new City("San Francisco", "CA", 883305, 231.89),
                new City("New York", "NY", 123456, 100)
        );

        var cityOperations = new CityOperations();

        // Test if IllegalStateException is thrown for duplicate keys
        assertThrows(IllegalStateException.class, () -> cityOperations.cityMapDuplicatesWithException(cities));
    }


    @Test
    public void testCityMapMapDuplicatesKeepExistingValue() {

        var cities = List.of(
                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234),
                new City("San Francisco", "CA", 883305, 231.89),
                new City("New York", "NY", 123456, 100)
        );

        var cityOperations = new CityOperations();

        var cityMap = cityOperations.cityMapMapDuplicatesKeepExistingValue(cities);

        // Expected result: Only one city instance should be retained for each key (name)
        assertEquals(4, cityMap.size());
        assertEquals(8623000, cityMap.get("New York").getPopulation());
        assertEquals(3994000, cityMap.get("Los Angeles").getPopulation());
        assertEquals(2716000, cityMap.get("Chicago").getPopulation());
        assertEquals(883305, cityMap.get("San Francisco").getPopulation());
    }

    @Test
    public void testCityMapMapDuplicatesReplaceExistingValue() {
        var cities = List.of(
                new City("New York", "NY", 8623000, 468.9),
                new City("Los Angeles", "CA", 3994000, 503),
                new City("Chicago", "IL", 2716000, 234),
                new City("San Francisco", "CA", 883305, 231.89),
                new City("New York", "NY", 123456, 100)
        );

        var cityOperations = new CityOperations();

        var cityMap = cityOperations.cityMapMapDuplicatesReplaceExistingValue(cities);

        // Expected result: The last city instance should replace the previous one for each key (name)
        assertEquals(4, cityMap.size());
        assertEquals(123456, cityMap.get("New York").getPopulation());
        assertEquals(3994000, cityMap.get("Los Angeles").getPopulation());
        assertEquals(2716000, cityMap.get("Chicago").getPopulation());
        assertEquals(883305, cityMap.get("San Francisco").getPopulation());
    }
}