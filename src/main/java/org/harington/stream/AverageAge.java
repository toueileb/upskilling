package org.harington.stream;

import org.harington.stream.model.Person;

import java.util.List;

public class AverageAge {

    public double calculateAverageAgeOver20(List<Person> people) {
        return people
                .stream()
                .mapToInt(Person::getAge)
                .filter(age -> age > 20)
                .average()
                .orElseThrow();
    }


}
