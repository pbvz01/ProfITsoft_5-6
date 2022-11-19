package ua.profitsoft.zakharov.app.solution2.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ua.profitsoft.zakharov.app.solution2.model.Fine;

import java.util.ArrayList;
import java.util.List;

public class FineSaxHandler extends DefaultHandler {
    private static final String FINE = "fine";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String TYPE = "type";
    private static final String FINE_AMOUNT = "fine_amount";
    private static final String DATA_TIME = "date_time";
    private List<Fine> fineList;

    @Override
    public void startDocument() {
        fineList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        if(qName.equals(FINE)){
            Fine fine = new Fine();
            if(attributes.getLength() > 0) {
                pushDataFine(fine, attributes);
            }
            fineList.add(fine);
        }
    }

    private void pushDataFine(Fine fine, Attributes attributes) {
        for(int i = 0; i < attributes.getLength(); i++) {
            switch (attributes.getQName(i)) {
                case FIRST_NAME -> fine.setFirst_name(attributes.getValue(i));
                case LAST_NAME -> fine.setLast_name(attributes.getValue(i));
                case FINE_AMOUNT -> fine.setFine_amount(Double.parseDouble(attributes.getValue(i)));
                case TYPE -> fine.setType(attributes.getValue(i));
                case DATA_TIME -> fine.setDate_time(attributes.getValue(i));
            }
        }

    }
    public List<Fine> getFinesFromXmlFile() {
        return fineList;
    }
}
