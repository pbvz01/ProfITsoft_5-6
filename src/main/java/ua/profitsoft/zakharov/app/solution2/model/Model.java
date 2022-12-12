package ua.profitsoft.zakharov.app.solution2.model;

import ua.profitsoft.zakharov.app.solution2.annotation.Property;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class Model {
    @Property(name = "stringProperty")
    private String stringProperty;
    @Property(name = "numberProperty")
    private int myNumber;
    @Property(format="dd.MM.yyyy HH:mm")
    private LocalDateTime timeProperty;

    public void setStringProperty(String name) {
        this.stringProperty = name;
    }

    public void setMyNumber(Integer myNumber) {
        this.myNumber = myNumber;
    }

    public void setTimeProperty(LocalDateTime timeProperty) {
        this.timeProperty = timeProperty;
    }

    @Override
    public String toString() {
        return "Model {" +
                "stringProperty='" + stringProperty + '\'' +
                ", myNumber=" + myNumber +
                ", timeProperty=" + timeProperty +
                '}';
    }
}
