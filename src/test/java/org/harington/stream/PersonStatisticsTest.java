package org.harington.stream;

import org.harington.stream.model.Person;
import org.harington.stream.util.Constants;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonStatisticsTest {

    @Test
    public void testComputeStatistics() {
        // Création d'une liste de personnes avec des âges
        var people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 18),
                new Person("David", 22)
        );

        // Création de l'instance de PersonStatistics à tester
        var personStatistics = new PersonStatistics();

        // Appel de la méthode à tester
        var statistics = personStatistics.computeStatistics(people);

        // Vérification des résultats
        assertEquals(30, (int) statistics.get(Constants.MAX));
        assertEquals(18, (int) statistics.get(Constants.MIN));
        assertEquals(23.75, (double) statistics.get(Constants.AVERAGE));
        assertEquals(95, (long) statistics.get(Constants.SUM));
        assertEquals(4, (long) statistics.get(Constants.COUNT));
    }

    @Test
    public void testComputeStatisticsEmptyList() {
        // Test avec une liste vide
        List<Person> emptyList = List.of();
        var personStatistics = new PersonStatistics();
        var statistics = personStatistics.computeStatistics(emptyList);
        assertFalse(statistics.isEmpty());
        assertEquals(Integer.MIN_VALUE, (int) statistics.get(Constants.MAX));
        assertEquals(Integer.MAX_VALUE, (int) statistics.get(Constants.MIN));
        assertEquals(0.0, (double) statistics.get(Constants.AVERAGE));
        assertEquals(0, (long) statistics.get(Constants.SUM));
        assertEquals(0, (long) statistics.get(Constants.COUNT));
    }

    @Test
    public void testComputeStatisticsSinglePerson() {
        // Test avec une seule personne dans la liste
        var singlePerson = List.of(new Person("Alice", 25));
        PersonStatistics personStatistics = new PersonStatistics();
        Map<String, Object> statistics = personStatistics.computeStatistics(singlePerson);
        assertEquals(25, (int) statistics.get(Constants.MAX));
        assertEquals(25, (int) statistics.get(Constants.MIN));
        assertEquals(25, (double) statistics.get(Constants.AVERAGE));
        assertEquals(25, (long) statistics.get(Constants.SUM));
        assertEquals(1, (long) statistics.get(Constants.COUNT));
    }
}
