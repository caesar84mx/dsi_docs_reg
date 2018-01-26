package com.caesar_84.dsi_docs_reg.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "handed_documents", uniqueConstraints = {@UniqueConstraint(columnNames = {"serial"},
        name = "doc_serial_unique_constraint")})
public class HandedDocument {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "department", nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "name_handed", nullable = false)
    private String nameHanded;

    @Column(name = "handed_date", nullable = false)
    private LocalDate handedDate;

    @Column(name = "returned", nullable = false)
    private Boolean returned;

    @Column(name = "returned_date")
    private LocalDate returnedDate;

    public HandedDocument(){}

    public HandedDocument(ViewableHandedDocument viewableHandedDocument) {
        this(viewableHandedDocument.getId(),
                viewableHandedDocument.getSerial(),
                viewableHandedDocument.getDescription(),
                viewableHandedDocument.getDepartment(),
                viewableHandedDocument.getNameHanded(),
                viewableHandedDocument.getHandedDate(),
                viewableHandedDocument.isReturned(),
                viewableHandedDocument.getReturnedDate());
    }

    public HandedDocument(Integer id,
                          String serial,
                          String description,
                          Department department,
                          String nameHanded,
                          LocalDate handedDate,
                          Boolean returned,
                          LocalDate returnedDate) {
        this.id = id;
        this.serial = serial;
        this.description = description;
        this.department = department;
        this.nameHanded = nameHanded;
        this.handedDate = handedDate;
        this.returned = returned;
        this.returnedDate = returnedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNameHanded() {
        return nameHanded;
    }

    public void setNameHanded(String nameHanded) {
        this.nameHanded = nameHanded;
    }

    public LocalDate getHandedDate() {
        return handedDate;
    }

    public void setHandedDate(LocalDate handedDate) {
        this.handedDate = handedDate;
    }

    public Boolean isReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
