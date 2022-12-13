package ua.profitsoft.zakharov.app.solution2.model;

import ua.profitsoft.zakharov.app.solution2.annotation.Property;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return myNumber == model.myNumber && Objects.equals(stringProperty, model.stringProperty) && Objects.equals(timeProperty, model.timeProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringProperty, myNumber, timeProperty);
    }
}
