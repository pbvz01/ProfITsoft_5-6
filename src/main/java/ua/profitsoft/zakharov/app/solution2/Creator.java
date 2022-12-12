package ua.profitsoft.zakharov.app.solution2;

import ua.profitsoft.zakharov.app.solution2.annotation.analyzer.PropertyAnalyzer;

import java.nio.file.Path;

public class Creator {
    public static final String DEFAULT_RESOURCES_PATH = "./src/main/resources/";
    public static final String FILE_NAME = "prop.properties";
    public static <T>T loadFromProperties(Class<T> cls, Path propertiesPath) {
        PropertyAnalyzer analyzer = new PropertyAnalyzer();
        T model = createObjByClass(cls);
        analyzer.analyzerProperty(cls, model,propertiesPath);
        return model;
    }

    private static  <T>T createObjByClass(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
