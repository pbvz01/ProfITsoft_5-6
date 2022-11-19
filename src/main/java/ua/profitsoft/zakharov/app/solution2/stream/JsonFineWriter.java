package ua.profitsoft.zakharov.app.solution2.stream;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import ua.profitsoft.zakharov.app.solution2.service.SourceDirectoryService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class JsonFineWriter {
    public void writeAnalyticByFinesToJsonFile(Map<String, Double> fineMap) {
        JsonFactory factory = new JsonFactory();
        try(JsonGenerator generator =
                    factory.createGenerator(new FileOutputStream(
                            SourceDirectoryService.getDefaultInputDirectory() + "/../Answer.json"), JsonEncoding.UTF8)) {

            generator.writeStartObject();
            for(Map.Entry<String, Double> fine : fineMap.entrySet()) {
                generator.writeStringField("type", fine.getKey());
                generator.writeNumberField("sum_amounts", fine.getValue());
            }
            generator.writeEndObject();

        } catch (IOException exp) {
            throw new RuntimeException(exp.getMessage());
        }

    }
}
