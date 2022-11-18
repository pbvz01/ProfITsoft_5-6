package ua.profitsoft.zakharov.app.solution2.service;

import ua.profitsoft.zakharov.app.solution2.model.Fine;
import ua.profitsoft.zakharov.app.solution2.service.utils.FineJsonUtils;
import ua.profitsoft.zakharov.app.solution2.service.utils.FineSaxUtils;

import java.util.List;

public class FineService {
    private FineSaxUtils utilsInput;
    private FineJsonUtils utilsOutput;

    public FineService() {
        utilsInput = new FineSaxUtils();
        utilsOutput = new FineJsonUtils();
    }

    public List<Fine> getAllFines() {
        return utilsInput.getAllFinesFromAllXmlFiles();
    }

    public void writeAllFinesToJson(List<Fine> fineList) {
        utilsOutput.writeAllFinesToJsonByType(fineList);
    }

}
