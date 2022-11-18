package ua.profitsoft.zakharov.app.solution2.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ua.profitsoft.zakharov.app.solution2.model.Fine;

import java.util.*;

public class FineHandlerSaxParserInput extends DefaultHandler {
    private static final String FINE = "fine";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String TYPE = "type";
    private static final String FINE_AMOUNT = "fine_amount";
    private static final String DATA_TIME = "date_time";
    private List<Fine> fineList;
    private Map<String, String> attributesMap;


    @Override
    public void startDocument() {
        fineList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        if(qName.equals(FINE)){
            fineList.add(new Fine());

            if(attributes.getLength() > 0) {
                attributesMap = new HashMap<>();
                for(int i = 0; i < attributes.getLength(); i++) {
                    attributesMap.put(attributes.getQName(i), attributes.getValue(i));
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(FINE)) {
            pushDataFine(attributesMap);
        }
    }

    private void pushDataFine(Map<String,String> attributesMap) {
        Fine fine = getActualFine();
        for(Map.Entry<String, String> map : attributesMap.entrySet()) {
            switch (map.getKey()) {
                case FIRST_NAME -> fine.setFirst_name(map.getValue());
                case LAST_NAME -> fine.setLast_name(map.getValue());
                case FINE_AMOUNT -> fine.setFine_amount(Double.parseDouble(map.getValue()));
                case TYPE -> fine.setType(map.getValue());
                case DATA_TIME -> fine.setDate_time(map.getValue());
            }
        }

    }
    private Fine getActualFine() {
        return fineList
                .get(fineList.size() - 1);
    }
    public List<Fine> getAllFinesFromXmlFile() {
        return fineList;
    }

}
