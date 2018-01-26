package com.caesar_84.dsi_docs_reg.view.controllers;

import com.caesar_84.dsi_docs_reg.domain.model.Department;
import com.caesar_84.dsi_docs_reg.domain.model.ViewableHandedDocument;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NewHandedDocumentDialogController {
    @FXML
    private TextField serialTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ChoiceBox<Department> departmentChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker handedDatePicker;

    @FXML
    private void handleOk(){
        viewableHandedDocument.setSerial(serialTextField.getText().toUpperCase());
        viewableHandedDocument.setDescription(descriptionTextArea.getText());
        viewableHandedDocument.setDepartment(departmentChoiceBox.getValue());
        viewableHandedDocument.setNameHanded(nameTextField.getText());
        viewableHandedDocument.setHandedDate(handedDatePicker.getValue());

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private Stage dialogStage;
    private boolean okClicked;
    private ViewableHandedDocument viewableHandedDocument;

    @FXML
    private void initialize() {
        serialTextField.setText("");
        descriptionTextArea.setText("");
        departmentChoiceBox.setItems(FXCollections.observableArrayList(Department.values()));
        nameTextField.setText("");
        handedDatePicker.setValue(LocalDate.now());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setViewableHandedDocument(ViewableHandedDocument viewableHandedDocument) {
        this.viewableHandedDocument = viewableHandedDocument;
    }

    public ViewableHandedDocument getViewableHandedDocument() {
        return viewableHandedDocument;
    }
}
