package ua.profitsoft.zakharov.app.solution2;

import ua.profitsoft.zakharov.app.solution2.model.Fine;
import ua.profitsoft.zakharov.app.solution2.service.FineService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dispatcher {
    static FineService fineService = new FineService();;

    public static void main(String[] args) {
            fineService.writeAllFinesToJson(fineService.getAllFines());

    }

}
