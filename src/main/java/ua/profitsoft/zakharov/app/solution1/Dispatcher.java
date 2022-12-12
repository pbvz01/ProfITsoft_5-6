package ua.profitsoft.zakharov.app.solution1;

import ua.profitsoft.zakharov.app.solution1.service.FineService;

public class Dispatcher {
    private static FineService fineService = new FineService();

    public static void main(String[] args) {
        //The results are in the file
        for(int i = 0; i < 10; i++) {
            System.out.println("Experiment " + (i + 1));
            fineService.getFinesFromXmlFiles();
        }


    }
}
