package ua.profitsoft.zakharov.app.solution2.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SourceDirectoryService {
    private final static String DEFAULT_INPUT_DIRECTORY = "./src/main/java/ua/profitsoft/zakharov/app/solution2/directory";
    public String inputDirectory;

    public SourceDirectoryService() {
        inputDirectory = DEFAULT_INPUT_DIRECTORY;
    }

    public SourceDirectoryService(String sourcePath) {
        inputDirectory = sourcePath;
    }

    public List<Path> getXmlFilesFromDirectory() {
        try(Stream<Path> paths = Files.walk(Path.of(inputDirectory))) {
            return paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    public String getInputDirectory() {
        return inputDirectory;
    }

    public void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    public static String getDefaultInputDirectory() {
        return DEFAULT_INPUT_DIRECTORY;
    }

}
