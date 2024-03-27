package org.harington.stream;

import org.harington.stream.model.City;
import org.harington.stream.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatMapOperationsTest {

    @Test
    public void testAverageAgeOfPeople() {

        // Création de quelques personnes avec des âges
        var person1 = new Person("Alice", 25);
        var person2 = new Person("Bob", 30);
        var person3 = new Person("Charlie", 18);

        // Création de quelques villes avec des personnes
        var people1 = List.of(person1, person2);
        var people2 = List.of(person3);

        var city1 = new City("City1", "State1", 1000, 50.0, people1);
        var city2 = new City("City2", "State2", 2000, 70.0, people2);

        List<City> cities = List.of(city1, city2);

        // Création de l'instance de CityOperations à tester
        var flatMapOperations = new FlatMapOperations();

        // Appel de la méthode à tester
        double averageAge = flatMapOperations.averageAgeOfPeople(cities);

        // Vérification du résultat
        assertEquals(24.33, averageAge, 0.01); // La moyenne est arrondie à deux décimales
    }

    @Test
    public void testFilterPeopleAboveAge() {

        // Création de quelques personnes avec des âges
        var person1 = new Person("Alice", 25);
        var person2 = new Person("Bob", 30);
        var person3 = new Person("Charlie", 18);

        // Création de quelques villes avec des personnes
        var people1 = List.of(person1, person2);
        var people2 = List.of(person3);

        var city1 = new City("City1", "State1", 1000, 50.0, people1);
        var city2 = new City("City2", "State2", 2000, 70.0, people2);

        var cities = List.of(city1, city2);

        // Âge seuil pour filtrer les personnes
        int ageThreshold = 20;

        // Création de l'instance de CityOperations à tester
        var flatMapOperations = new FlatMapOperations();

        // Appel de la méthode à tester
        var filteredPeople = flatMapOperations.filterPeopleAboveAge(cities, ageThreshold);

        // Vérification du résultat
        assertEquals(2, filteredPeople.size()); // On s'attend à ce que deux personnes soient filtrées
        assertEquals("Alice", filteredPeople.get(0).getName());
        assertEquals("Bob", filteredPeople.get(1).getName());
    }


    @Test
    public void testGroupPeopleByAge() {

        // Création de quelques personnes avec des âges
        var person1 = new Person("Alice", 25);
        var person2 = new Person("Bob", 30);
        var person3 = new Person("Charlie", 18);
        var person4 = new Person("David", 25);

        // Création de quelques villes avec des personnes
        var people1 = List.of(person1, person2);
        var people2 = List.of(person3, person4);

        var city1 = new City("City1", "State1", 1000, 50.0, people1);
        var city2 = new City("City2", "State2", 2000, 70.0, people2);

        var cities = List.of(city1, city2);

        // Création de l'instance de CityOperations à tester
        var flatMapOperations = new FlatMapOperations();

        // Appel de la méthode à tester
        var groupedPeopleByAge = flatMapOperations.groupPeopleByAge(cities);

        // Vérification du résultat
        assertEquals(2, groupedPeopleByAge.get(25).size()); // On s'attend à ce qu'il y ait deux personnes de 25 ans
        assertEquals(1, groupedPeopleByAge.get(30).size()); // On s'attend à ce qu'il y ait une personne de 30 ans
        assertEquals(1, groupedPeopleByAge.get(18).size()); // On s'attend à ce qu'il y ait une personne de 18 ans
    }


    @Test
    public void testCountTotalPeople() {
        // Création de quelques personnes avec des âges
        var person1 = new Person("Alice", 25);
        var person2 = new Person("Bob", 30);
        var person3 = new Person("Charlie", 18);
        var person4 = new Person("David", 25);

        // Création de quelques villes avec des personnes
        var people1 = List.of(person1, person2);
        var people2 = List.of(person3, person4);

        var city1 = new City("City1", "State1", 1000, 50.0, people1);
        var city2 = new City("City2", "State2", 2000, 70.0, people2);

        var cities = List.of(city1, city2);

        // Création de l'instance de CityOperations à tester
        var flatMapOperations = new FlatMapOperations();

        // Appel de la méthode à tester
        long totalPeopleCount = flatMapOperations.countTotalPeople(cities);

        // Vérification du résultat
        assertEquals(4, totalPeopleCount); // On s'attend à ce qu'il y ait 4 personnes au total
    }

    @Test
    public void testSortPeopleByAge() {
        // Création de quelques personnes avec des âges
        var person1 = new Person("Alice", 25);
        var person2 = new Person("Bob", 30);
        var person3 = new Person("Charlie", 18);
        var person4 = new Person("David", 22);

        // Création de quelques villes avec des personnes
        var people1 = List.of(person1, person2);
        var people2 = List.of(person3, person4);

        var city1 = new City("City1", "State1", 1000, 50.0, people1);
        var city2 = new City("City2", "State2", 2000, 70.0, people2);

        var cities = List.of(city1, city2);

        // Création de l'instance de CityOperations à tester
        var flatMapOperations = new FlatMapOperations();

        // Appel de la méthode à tester
        var sortedPeople = flatMapOperations.sortPeopleByAge(cities);

        // Vérification du résultat
        assertEquals(18, sortedPeople.get(0).getAge()); // Charlie
        assertEquals(22, sortedPeople.get(1).getAge()); // David
        assertEquals(25, sortedPeople.get(2).getAge()); // Alice
        assertEquals(30, sortedPeople.get(3).getAge()); // Bob
    }
}