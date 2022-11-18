package ua.profitsoft.zakharov.app.solution2.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryService {
    private static String WORK_DIRECTORY = "./src/main/java/ua/profitsoft/zakharov/app/solution2/directory";

    public List<Path> getAllXmlFilesFromDirectory() {
        try {
            return Files.walk(Path.of(WORK_DIRECTORY))
                    .filter(file -> Files.isRegularFile(file))
                    .collect(Collectors.toList());
        } catch (IOException exp) {
            throw new RuntimeException("Directory was not founded");
        }
    }

    //Added for useful. If we want to edit source directory, we can use it
    public static void setPathDirectory(String fullPath) {
        WORK_DIRECTORY = fullPath;
    }

    public static String getPathDirectory() {
        return WORK_DIRECTORY;
    }

}
