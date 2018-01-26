package com.caesar_84.dsi_docs_reg.domain.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ViewableHandedDocument {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty serial = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<Department> department = new SimpleObjectProperty<>();
    private StringProperty nameHanded = new SimpleStringProperty();
    private ObjectProperty<LocalDate> handedDate = new SimpleObjectProperty<>();
    private BooleanProperty returned = new SimpleBooleanProperty();
    private ObjectProperty<LocalDate> returnedDate = new SimpleObjectProperty<>();

    public ViewableHandedDocument() {}

    public ViewableHandedDocument(HandedDocument handedDocument) {
        this(new SimpleIntegerProperty(handedDocument.getId()),
                new SimpleStringProperty(handedDocument.getSerial()),
                new SimpleStringProperty(handedDocument.getDescription()),
                new SimpleObjectProperty<Department>(handedDocument.getDepartment()),
                new SimpleStringProperty(handedDocument.getNameHanded()),
                new SimpleObjectProperty<LocalDate>(handedDocument.getHandedDate()),
                new SimpleBooleanProperty(handedDocument.isReturned()),
                new SimpleObjectProperty<LocalDate>(handedDocument.getReturnedDate()));
    }

    public ViewableHandedDocument(IntegerProperty id,
                                  StringProperty serial,
                                  StringProperty description,
                                  ObjectProperty<Department> department,
                                  StringProperty nameHanded,
                                  ObjectProperty<LocalDate> handedDate,
                                  BooleanProperty returned,
                                  ObjectProperty<LocalDate> returnedDate) {
        this.id = id;
        this.serial = serial;
        this.description = description;
        this.department = department;
        this.nameHanded = nameHanded;
        this.handedDate = handedDate;
        this.returned = returned;
        this.returnedDate = returnedDate;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSerial() {
        return serial.get();
    }

    public StringProperty serialProperty() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial.set(serial);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Department getDepartment() {
        return department.get();
    }

    public ObjectProperty<Department> departmentProperty() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department.set(department);
    }

    public String getNameHanded() {
        return nameHanded.get();
    }

    public StringProperty nameHandedProperty() {
        return nameHanded;
    }

    public void setNameHanded(String nameHanded) {
        this.nameHanded.set(nameHanded);
    }

    public LocalDate getHandedDate() {
        return handedDate.get();
    }

    public ObjectProperty<LocalDate> handedDateProperty() {
        return handedDate;
    }

    public void setHandedDate(LocalDate handedDate) {
        this.handedDate.set(handedDate);
    }

    public boolean isReturned() {
        return returned.get();
    }

    public BooleanProperty returnedProperty() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned.set(returned);
    }

    public LocalDate getReturnedDate() {
        return returnedDate.get();
    }

    public ObjectProperty<LocalDate> returnedDateProperty() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate.set(returnedDate);
    }
}
