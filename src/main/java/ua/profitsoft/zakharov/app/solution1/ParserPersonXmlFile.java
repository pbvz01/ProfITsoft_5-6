package ua.profitsoft.zakharov.app.solution1;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserPersonXmlFile {
    private final static String DIRECTORY_PATH = "./src/main/java/ua/profitsoft/zakharov/app/solution1/";
    private final static String REGEX_PERSON_ELEMENT = "\\s*(<person)(($)|(\\s.+))";
    private final static String REGEX_SURNAME_ATTRIBUTE = "\\b(surname)\\s*=\\s*\"([^\"]+)\"";
    private final static String REGEX_NAME_ATTRIBUTE = "\\b(name)\\s*=\\s*\"([^\"]+)\"";
    private final static String REGEX_EMPTY_LINE = "(?m)^[ \\t]*\\r?\\n";
    private final static String REGEX_JOIN_ATTRIBUTES = "\\b(surname)\\s*=\\s*\"";

    public void parseXmlFile(String nameFile) {
        Path inputPath = Path.of(DIRECTORY_PATH + nameFile);
        Path outPath = Path.of(DIRECTORY_PATH + "copy" + nameFile);

        try(BufferedReader reader = new BufferedReader(new FileReader(inputPath.toFile()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outPath.toFile()))) {

          while (reader.ready()) {
              String xmlElement = reader.readLine();

              if (isPersonXmlElement(xmlElement)) {
                  while(isXmlElementNotClosed(xmlElement)) {
                      xmlElement += "\n" + reader.readLine();
                  }
                  xmlElement = parseXmlAttributes(xmlElement);
              }

              writer.write(xmlElement);
              writer.newLine();
          }

        } catch (IOException exp) {
            throw new RuntimeException(exp.getMessage());
        }

    }
    private boolean isPersonXmlElement(String xmlElement) {
        return xmlElement.matches(REGEX_PERSON_ELEMENT);
    }
    private boolean isXmlElementNotClosed(String xmlElement) {
        return !(xmlElement.endsWith("/>"));
    }
    private String parseXmlAttributes(String xmlElement) {
        String surname = getSurnameAttribute(xmlElement);
        String name = getNameAttribute(xmlElement);
        String fullName = joinNameAndSurnameAttribute(name, surname);

        String parseXmlElement = xmlElement.replace(" " + surname, "");
        parseXmlElement = parseXmlElement.replace(name, fullName);
        parseXmlElement = parseXmlElement.replaceAll(REGEX_EMPTY_LINE, "");
        return parseXmlElement;
    }
    private String getSurnameAttribute(String line) {
        Pattern pattern = Pattern.compile(REGEX_SURNAME_ATTRIBUTE);
        Matcher match = pattern.matcher(line);
        String surnameAttribute = "";

        while (match.find()) {
            surnameAttribute = match.group();
        }

        return surnameAttribute;
    }
    private String getNameAttribute(String line) {
        Pattern pattern = Pattern.compile(REGEX_NAME_ATTRIBUTE);
        Matcher match = pattern.matcher(line);
        String nameAttribute = "";

        while (match.find()) {
            nameAttribute = match.group();
        }

        return nameAttribute;
    }
    private String joinNameAndSurnameAttribute(String atrName, String atrSurname) {
        StringBuilder builder = new StringBuilder(atrName);
        return builder
                .deleteCharAt(atrName.length() - 1)
                .append(atrSurname.replaceAll(REGEX_JOIN_ATTRIBUTES, " "))
                .toString();
    }
}
