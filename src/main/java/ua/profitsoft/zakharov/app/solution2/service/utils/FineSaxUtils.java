package ua.profitsoft.zakharov.app.solution2.service.utils;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ua.profitsoft.zakharov.app.solution2.handler.FineHandlerJsonParserOutput;
import ua.profitsoft.zakharov.app.solution2.handler.FineHandlerSaxParserInput;
import ua.profitsoft.zakharov.app.solution2.model.Fine;
import ua.profitsoft.zakharov.app.solution2.service.DirectoryService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FineSaxUtils {
    private DirectoryService directoryService;
    private SAXParser parser;
    private FineHandlerSaxParserInput fineHandlerSaxParserInput;
    private FineHandlerJsonParserOutput fineHandler;

    public FineSaxUtils() {
        directoryService = new DirectoryService();
        fineHandlerSaxParserInput = new FineHandlerSaxParserInput();

        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
        }
        catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

    }

    public List<Fine> getAllFinesFromAllXmlFiles() {
        return readAllFilesFromDirectory();
    }

    private List<Fine> readAllFilesFromDirectory() {
        List <Fine> allFineList = new ArrayList<>();
        List<Path> allFiles = directoryService.getAllXmlFilesFromDirectory();
        for(Path file : allFiles) {
            allFineList.addAll(readFileFromDirectory(file));
        }
        return allFineList;
    }

    private List<Fine> readFileFromDirectory(Path path) {
        List<Fine> fineList = new ArrayList<>();

        if (this.parser != null) {
            InputSource input = new InputSource(path.toString());
            try {
                parser.parse(input, this.fineHandlerSaxParserInput);
                fineList = this.fineHandlerSaxParserInput.getAllFinesFromXmlFile();
            }
            catch (SAXException | IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return fineList;
    }

}
