package ua.profitsoft.zakharov.app.solution2.analytics;

import ua.profitsoft.zakharov.app.solution2.model.Fine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FineAnalytics {
    public static Map<String, Double> getTypeAnalyticsByFine(List<Fine> fineList) {
        return fineList.stream()
                .collect(Collectors.groupingBy(Fine::getType, Collectors.summingDouble(Fine::getFine_amount)))
                .entrySet()
                .stream()
                .sorted((el1, el2) -> (int)(el2.getValue() - el1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}
