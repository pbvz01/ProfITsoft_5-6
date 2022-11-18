package ua.profitsoft.zakharov.app.solution2.service.utils;

import ua.profitsoft.zakharov.app.solution2.handler.FineHandlerJsonParserOutput;
import ua.profitsoft.zakharov.app.solution2.model.Fine;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FineJsonUtils {
    private FineHandlerJsonParserOutput fineHandler;

    public FineJsonUtils() {
        fineHandler = new FineHandlerJsonParserOutput();
    }

    public void writeAllFinesToJsonByType(List<Fine> fineList) {
        Map<String, List<Fine>> fineMapByType = fineList
                .stream()
                .sorted((el1, el2) -> (int)(el2.getFine_amount() - el1.getFine_amount()))
                .collect(Collectors.groupingBy(Fine::getType));

        fineHandler.writeFinesToJsonFileByType(fineMapByType);
    }

}
