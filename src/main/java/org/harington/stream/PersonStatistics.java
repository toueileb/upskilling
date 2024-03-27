package org.harington.stream;

import org.harington.stream.model.Person;
import org.harington.stream.util.Constants;

import java.util.List;
import java.util.Map;

public class PersonStatistics {

    public Map<String, Object> computeStatistics(List<Person> people) {
        var summaryStatistics = people.stream()
                .mapToInt(Person::getAge)
                .summaryStatistics();

        return Map.of(Constants.MAX, summaryStatistics.getMax(),
                Constants.MIN, summaryStatistics.getMin(),
                Constants.AVERAGE, summaryStatistics.getAverage(),
                Constants.SUM, summaryStatistics.getSum(),
                Constants.COUNT, summaryStatistics.getCount());
    }
}
