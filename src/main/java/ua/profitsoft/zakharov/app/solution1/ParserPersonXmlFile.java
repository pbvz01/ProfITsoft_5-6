package ua.profitsoft.zakharov.app.solution1;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserPersonXmlFile {
    private final static String DIRECTORY_PATH = "./src/main/java/ua/profitsoft/zakharov/app/solution1/";
    private final static String REGEX_ATTRIBUTE_SURNAME = "\\b(surname)\\s*=\\s*\"([^\"]+)\"";
    private final static String REGEX_ATTRIBUTE_NAME = "\\b(name)\\s*=\\s*\"([^\"]+)\"";

    public void parseXmlFile(String nameFile) {
        Path inputPath = Path.of(DIRECTORY_PATH + nameFile);
        Path outPath = Path.of(DIRECTORY_PATH + "Copy_" + nameFile);

        try(BufferedReader reader = new BufferedReader(new FileReader(inputPath.toFile()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outPath.toFile()))) {

          while (reader.ready()) {
              String xmlElement = reader.readLine();

              if (isNotPersonsXmlElement(xmlElement)) {
                  while(isXmlElementNotClosed(xmlElement)) {
                      xmlElement += "\n" + reader.readLine();
                  }
                  xmlElement = parseXmlAttributes(xmlElement);
              }

              writer.write(xmlElement);
              writer.newLine();
          }

        } catch (IOException exp) {
            throw new RuntimeException("File not found. File must be in the class's directory");
        }

    }

    private boolean isNotPersonsXmlElement(String xmlElement) {
        return !(xmlElement.startsWith("<persons>")) && !(xmlElement.startsWith("</persons>"));
    }
    private boolean isXmlElementNotClosed(String xmlElement) {
        return !(xmlElement.endsWith("/>"));
    }
    private String parseXmlAttributes(String xmlElement) {
        String attributeSurname = getAttributeSurname(xmlElement);
        String attributeName = getAttributeName(xmlElement);
        String correctAttributeName = getAttributeNameJoinSurname(attributeName, attributeSurname);

        String parseXmlElement = xmlElement.replace(" " + attributeSurname, "");
        parseXmlElement = parseXmlElement.replace(attributeName, correctAttributeName);
        return parseXmlElement;
    }
    private String getAttributeSurname(String line) {
        Pattern pattern = Pattern.compile(REGEX_ATTRIBUTE_SURNAME);
        Matcher match = pattern.matcher(line);
        String attributeSurname = "";

        while (match.find()) {
            attributeSurname = match.group();
        }

        return attributeSurname;
    }
    private String getAttributeName(String line) {
        Pattern pattern = Pattern.compile(REGEX_ATTRIBUTE_NAME);
        Matcher match = pattern.matcher(line);
        String attributeName = "";

        while (match.find()) {
            attributeName = match.group();
        }

        return attributeName;
    }
    private String getAttributeNameJoinSurname(String atrName, String atrSurname) {
        StringBuilder builder = new StringBuilder(atrName);
        return builder
                .deleteCharAt(atrName.length() - 1)
                .append(atrSurname.replaceAll("\\b(surname)\\s*=\\s*\"", " "))
                .toString();
    }
}
