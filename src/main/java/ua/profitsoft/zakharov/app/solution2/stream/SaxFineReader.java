package ua.profitsoft.zakharov.app.solution2.stream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ua.profitsoft.zakharov.app.solution2.handler.FineSaxHandler;
import ua.profitsoft.zakharov.app.solution2.model.Fine;
import ua.profitsoft.zakharov.app.solution2.service.SourceDirectoryService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaxFineReader {
    private SourceDirectoryService sourceDirectoryService;
    private SAXParser parser;

    public SaxFineReader() {
        sourceDirectoryService = new SourceDirectoryService();

        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
        }
        catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public List<Fine> getFinesFromXmlFiles() {
        return readFilesFromDirectory();
    }

    private List<Fine> readFilesFromDirectory() {
        List <Fine> allFineList = new ArrayList<>();
        List<Path> allFiles = sourceDirectoryService.getXmlFilesFromDirectory();
        for(Path file : allFiles) {
            allFineList.addAll(readFileFromDirectory(file));
        }
        return allFineList;
    }

    private List<Fine> readFileFromDirectory(Path path) {
        List<Fine> fineList = new ArrayList<>();

        if (this.parser != null) {
            InputSource input = new InputSource(path.toString());
            FineSaxHandler handler = new FineSaxHandler();
            try {
                parser.parse(input, handler);
                fineList = handler.getFinesFromXmlFile();
            }
            catch (SAXException | IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return fineList;
    }

}
