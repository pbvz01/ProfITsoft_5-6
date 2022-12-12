package ua.profitsoft.zakharov.app.solution1.stream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ua.profitsoft.zakharov.app.solution1.handler.FineSaxHandler;
import ua.profitsoft.zakharov.app.solution1.model.Fine;
import ua.profitsoft.zakharov.app.solution1.service.SourceDirectoryService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class SaxFineReader {
    private final SourceDirectoryService sourceDirectoryService;

    public SaxFineReader() {
        sourceDirectoryService = new SourceDirectoryService();
    }

    public List<Fine> getFinesFromXmlFiles() {
        return readFilesFromDirectory();
    }

    private List<Fine> readFilesFromDirectory() {

        List<Path> files = sourceDirectoryService.getXmlFilesFromDirectory();

        long time = System.currentTimeMillis();
        List<Fine> fines = files.stream()
                .map(this::parseFile)
                .flatMap(Collection::stream)
                .toList();
        System.out.println("Time of execution: " + (System.currentTimeMillis() - time));
        return fines;




        /*
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<Path> files = sourceDirectoryService.getXmlFilesFromDirectory();
        List<Fine> fines = Collections.synchronizedList(new ArrayList<>());
        long time = System.currentTimeMillis();
        List<CompletableFuture<Void>> futures = files.stream()
                .map(file -> CompletableFuture.supplyAsync(() -> parseFile(file)
                , executorService).thenAccept(fines::addAll))
                .toList();

        CompletableFuture<Void> combined =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        combined.join();
        awaitTerminationAfterShutdown(executorService);

        System.out.println("Time of execution: " + (System.currentTimeMillis() - time));
        return fines;

         */
    }

    private synchronized List<Fine> parseFile(Path path) {
        SAXParser parser;
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
        }
        catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e.getMessage());
        }
        List<Fine> fineList = new ArrayList<>();

        if (parser != null) {
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

    private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
