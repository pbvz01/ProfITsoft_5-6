package ua.profitsoft.zakharov.app.solution2.handler;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import ua.profitsoft.zakharov.app.solution2.model.Fine;
import ua.profitsoft.zakharov.app.solution2.service.DirectoryService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FineHandlerJsonParserOutput {
    public void writeFinesToJsonFileByType(Map<String, List<Fine>> fineMap) {
        JsonFactory factory = new JsonFactory();
        try(JsonGenerator generator =
                    factory.createGenerator(new FileOutputStream(
                            DirectoryService.getPathDirectory() + "/Answer.json"), JsonEncoding.UTF8)) {
            for(Map.Entry<String, List<Fine>> fine : fineMap.entrySet()) {
                generator.writeStartObject();
                generator.writeStringField("type", fine.getKey());
                generator.writeFieldName("fine_amounts");
                generator.writeStartArray();
                fine.getValue()
                        .forEach(elementFine -> {try {
                            generator.writeNumber(elementFine.getFine_amount());
                        }catch (IOException exp) {
                            throw new RuntimeException(exp.getMessage());
                        }

                });
                generator.writeEndArray();
                generator.writeEndObject();
            }

        } catch (IOException exp) {
            throw new RuntimeException(exp.getMessage());
        }

    }
}
