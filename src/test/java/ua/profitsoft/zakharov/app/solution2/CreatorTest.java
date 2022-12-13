package ua.profitsoft.zakharov.app.solution2;

import org.junit.jupiter.api.Test;
import ua.profitsoft.zakharov.app.solution2.model.Model;

import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CreatorTest {
    private final static String sourceTestProperties = "./src/main/resources/test.properties";
    private static Model resultModel;

    static {
        resultModel = new Model();
        resultModel.setStringProperty("model");
        resultModel.setMyNumber(10000);
        resultModel.setTimeProperty(LocalDateTime.of(2022, 11, 29, 18, 30));
    }

    @Test
    void loadFromProperties() {
        Model model = Creator.loadFromProperties(Model.class, Path.of(sourceTestProperties));
        assertEquals(model, resultModel);
    }
}