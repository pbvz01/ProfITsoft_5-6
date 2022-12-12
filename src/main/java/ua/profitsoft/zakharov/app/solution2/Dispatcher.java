package ua.profitsoft.zakharov.app.solution2;

import ua.profitsoft.zakharov.app.solution2.model.Model;

import java.nio.file.Path;

public class Dispatcher {
    public static void main(String[] args) {
        Class<Model> cls = Model.class;
        Path propertiesPath = Path.of(Creator.DEFAULT_RESOURCES_PATH + Creator.FILE_NAME);
        Model model = Creator.loadFromProperties(cls, propertiesPath);
        System.out.println(model);
    }

}
