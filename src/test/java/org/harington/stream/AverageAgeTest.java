package org.harington.stream;

import org.harington.stream.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AverageAgeTest {

    @Test
    public void testCalculateAverageAgeOver20() {
        // Scénario de base avec des personnes de plus de 20 ans
        var people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 18),
                new Person("David", 22)
        );
        var personStatistics = new AverageAge();

        var averageAge = personStatistics.calculateAverageAgeOver20(people);
        assertEquals(25.666666666666668, averageAge);

        // Scénario avec une seule personne de plus de 20 ans
        var singlePerson = Collections.singletonList(new Person("Eve", 22));
        double singleAverageAge = personStatistics.calculateAverageAgeOver20(singlePerson);
        assertEquals(22, singleAverageAge);

        // Scénario avec aucune personne de plus de 20 ans
        var noPeopleOver20 = List.of(
                new Person("Frank", 18),
                new Person("Grace", 19),
                new Person("Henry", 20)
        );
        assertThrows(NoSuchElementException.class, () -> personStatistics.calculateAverageAgeOver20(noPeopleOver20));
    }
}