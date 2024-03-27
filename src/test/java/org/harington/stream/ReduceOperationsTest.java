package org.harington.stream;

import org.harington.stream.model.City;
import org.harington.stream.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReduceOperationsTest {

    @Test
    public void testSumOfAllAges() {
        // Création de personnes avec différents âges
        var people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 18),
                new Person("David", 22)
        );

        // Création de villes avec ces personnes
        var city1 = new City("City1", "State1", 1000, 50.5, people);
        City city2 = new City("City2", "State2", 1500, 60.2, List.of());
        var cities = List.of(city1, city2);

        // Instance de ReduceOperations
        var reduceOperations = new ReduceOperations();

        // Appel de la méthode à tester
        int sum = reduceOperations.sumOfAllAges(cities);

        // Vérification du résultat
        assertEquals(25 + 30 + 18 + 22, sum);
    }

    @Test
    public void testFindOldestPerson() {
        // Création de personnes avec différents âges
        List<Person> people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 18),
                new Person("David", 22)
        );

        // Création de villes avec ces personnes
        var city1 = new City("City1", "State1", 1000, 50.5, people);
        var city2 = new City("City2", "State2", 1500, 60.2, people);
        var cities = List.of(city1, city2);

        // Instance de ReduceOperations
        var reduceOperations = new ReduceOperations();

        // Appel de la méthode à tester
        var oldestPersonOptional = reduceOperations.findOldestPerson(cities);

        // Vérification du résultat
        assertTrue(oldestPersonOptional.isPresent());
        assertEquals("Bob", oldestPersonOptional.get().getName());
        assertEquals(30, oldestPersonOptional.get().getAge());
    }

    @Test
    public void testConcatenateAllNames() {
        // Création de personnes avec différents noms
        List<Person> people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 18),
                new Person("David", 22)
        );

        // Création de villes avec ces personnes
        var city1 = new City("City1", "State1", 1000, 50.5, people);
        var city2 = new City("City2", "State2", 1500, 60.2, List.of());
        var cities = List.of(city1, city2);

        // Instance de ReduceOperations
        var reduceOperations = new ReduceOperations();

        // Appel de la méthode à tester
        String concatenatedNames = reduceOperations.concatenateAllNames(cities);

        // Vérification du résultat
        String expectedConcatenation = "Alice Bob Charlie David";
        assertEquals(expectedConcatenation, concatenatedNames);
    }

    @Test
    public void testTotalPopulation() {
        // Création de villes avec des populations connues
        var cities = List.of(
                new City("City1", "State1", 1000, 50.5),
                new City("City2", "State2", 1500, 60.2),
                new City("City3", "State3", 2000, 70.7)
        );

        // Instance de ReduceOperations
        var reduceOperations = new ReduceOperations();

        // Appel de la méthode à tester
        int totalPopulation = reduceOperations.totalPopulation(cities);

        // Somme attendue des populations
        int expectedTotalPopulation = 1000 + 1500 + 2000;

        // Vérification du résultat
        assertEquals(expectedTotalPopulation, totalPopulation);
    }

    @Test
    public void testIsAnyPersonAboveAgeThreshold() {
        // Création de villes avec des personnes dont certaines ont un âge supérieur au seuil
        var cities = List.of(
                new City("City1", "State1", 1000, 50.5, List.of(
                        new Person("Alice", 25),
                        new Person("Bob", 30),
                        new Person("Charlie", 18)
                )),
                new City("City2", "State2", 1500, 60.2, List.of(
                        new Person("David", 22),
                        new Person("Eve", 35),
                        new Person("Frank", 40)
                ))
        );

        // Instance de ReduceOperations
        var reduceOperations = new ReduceOperations();

        // Seuil d'âge
        int ageThreshold = 35;

        // Appel de la méthode à tester
        boolean result = reduceOperations.isAnyPersonAboveAgeThreshold(cities, ageThreshold);

        // Nous nous attendons à ce qu'au moins une personne dans les villes ait un âge supérieur au seuil
        assertTrue(result);
    }

    @Test
    public void testIsAnyPersonAboveAgeThreshold_NoPersonAboveThreshold() {
        // Création de villes avec des personnes dont aucun n'a un âge supérieur au seuil
        var cities = List.of(
                new City("City1", "State1", 1000, 50.5, List.of(
                        new Person("Alice", 25),
                        new Person("Bob", 30),
                        new Person("Charlie", 18)
                )),
                new City("City2", "State2", 1500, 60.2, List.of(
                        new Person("David", 22),
                        new Person("Eve", 30),
                        new Person("Frank", 32)
                ))
        );

        // Instance de ReduceOperations
        var reduceOperations = new ReduceOperations();

        // Seuil d'âge
        int ageThreshold = 35;

        // Appel de la méthode à tester
        boolean result = reduceOperations.isAnyPersonAboveAgeThreshold(cities, ageThreshold);

        // Nous nous attendons à ce qu'aucune personne dans les villes n'ait un âge supérieur au seuil
        assertFalse(result);
    }
}