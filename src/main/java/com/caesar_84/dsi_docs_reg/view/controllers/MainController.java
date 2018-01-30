package com.caesar_84.dsi_docs_reg.view.controllers;

import com.caesar_84.dsi_docs_reg.Main;
import com.caesar_84.dsi_docs_reg.domain.model.ViewableHandedDocument;
import com.caesar_84.dsi_docs_reg.service.HandedDocumentService;
import com.caesar_84.dsi_docs_reg.util.DateUtil;
import com.caesar_84.dsi_docs_reg.util.exceptions.WrongRecordDateException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Optional;

public class MainController {

    //  Documents table related
    @FXML
    private TableView<ViewableHandedDocument> handedDocumentTable;

    @FXML
    private TableColumn<ViewableHandedDocument, String> serialColumn;

    @FXML
    private TableColumn<ViewableHandedDocument, String> departmentColumn;

    @FXML
    private TableColumn<ViewableHandedDocument, String> nameColumn;

    @FXML
    private TableColumn<ViewableHandedDocument, String> handedDateColumn;

    @FXML
    private TableColumn<ViewableHandedDocument, String> isReturnedColumn;

    @FXML
    private TableColumn<ViewableHandedDocument, String> returnedDateColumn;

    //  Labels in details related
    @FXML
    private Label serialLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label departmentLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label handedDateLabel;

    @FXML
    private Label isReturnedLabel;

    @FXML
    private Label returnedDateLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button returnButton;
//
//    @FXML
//    private Button deleteButton;

    //  Events handling
    @FXML
    private void handleClose() {
        System.exit(0);
    }

    @FXML
    private void handleAddDocument() {
        boolean okClicked = main.showNewDocumentDialog(new ViewableHandedDocument());

        if (okClicked) {
            ViewableHandedDocument handedDocument = main.getNewHandedDocumentDialogController().getViewableHandedDocument();
            try {
                service.save(handedDocument);
                handedDocumentTable.setItems(service.getAll());
            } catch (WrongRecordDateException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleReturnDocument() {
        int selectedIndex = handedDocumentTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            ViewableHandedDocument handedDocument = service.get(handedDocumentTable.getSelectionModel().getSelectedItem().getId());

            if (!handedDocument.isReturned()) {
                Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationDialog.setTitle(Main.APP_NAME);
                confirmationDialog.setHeaderText("¿Devolver Documento?");
                confirmationDialog.setContentText("¿Quiere devolver el documento?");

                Optional<ButtonType> result = confirmationDialog.showAndWait();
                if (result.get() == ButtonType.OK) {

                    handedDocument.setReturned(true);
                    handedDocument.setReturnedDate(LocalDate.now());

                    try {
                        service.update(handedDocument);
                        handedDocumentTable.setItems(service.getAll());
                    } catch (WrongRecordDateException e) {
                        e.printStackTrace();
                    }
                    confirmationDialog.close();
                } else {
                    confirmationDialog.close();
                }
            } else {
                showAlertWindow(Alert.AlertType.WARNING,
                        "Documento Ya Devuelto",
                        "El documento ya está devuelto.");
            }
        } else {
            showAlertWindow(Alert.AlertType.WARNING,
                    "Documento No Seleccionado",
                    "Por favor, seleccione un documento en la tabla.");
        }
    }

//    @FXML
//    private void handleDeleteDocument() {
//        int selectedIndex = handedDocumentTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
//            confirmationDialog.setTitle("DSI: REGISTRO DE DOCUMENTOS");
//            confirmationDialog.setHeaderText("¿Eliminar Documento?");
//            confirmationDialog.setContentText("¿Quiere eliminar el documento?");
//
//            Optional<ButtonType> result = confirmationDialog.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                service.delete(handedDocumentTable.getSelectionModel().getSelectedItem().getId());
//                handedDocumentTable.setItems(service.getAll());
//                confirmationDialog.close();
//            } else {
//                confirmationDialog.close();
//            }
//        } else {
//            showAlertWindow(Alert.AlertType.WARNING,
//                    "Documento No Seleccionado",
//                    "Por favor, seleccione un documento en la tabla.");
//        }
//    }

    @FXML
    private void handleAbout() {
        showAlertWindow(Alert.AlertType.INFORMATION, "Sobre la aplicación", "Esta aplicación está diseñada para llevar el registro de los documentos entrantes." +
                " En caso de cualquier duda, comuníquese conmigo al correo dymnov.dm@gmail.com." +
                "\n\n\nDiseñado por Maxim Dymnov");
    }

    //----------------------------------------------------------------------------------------------------------------------
    @FXML
    private void initialize() {
        serialColumn.setStyle("-fx-alignment: CENTER");
        serialColumn.setCellValueFactory(cellData -> cellData.getValue().serialProperty());

        departmentColumn.setStyle("-fx-alignment: CENTER");
        departmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty().asString());

        nameColumn.setStyle("-fx-alignment: CENTER");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameHandedProperty());

        handedDateColumn.setStyle("-fx-alignment: CENTER");
        handedDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtil.format(cellData.getValue().getHandedDate())));

        isReturnedColumn.setStyle("-fx-alignment: CENTER");
        isReturnedColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().isReturned()) {
                return new SimpleStringProperty("SÍ");
            } else {
                return new SimpleStringProperty("NO");
            }
        });

        returnedDateColumn.setStyle("-fx-alignment: CENTER");
        returnedDateColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getReturnedDate() != null) {
                return new SimpleStringProperty(DateUtil.format(cellData.getValue().getReturnedDate()));
            } else {
                return new SimpleStringProperty("NO HAY");
            }
        });

        showHandedDocumentDetails(null);
        handedDocumentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHandedDocumentDetails(newValue));
    }
//----------------------------------------------------------------------------------------------------------------------

    public MainController() {
    }

    private Main main;

    private HandedDocumentService service = HandedDocumentService.getInstance();

    public void setMain(Main main) {
        this.main = main;

        Platform.runLater(()-> handedDocumentTable.setItems(service.getAll()));
    }

    private void showAlertWindow(Alert.AlertType type, String header, String text) {
        Alert window = new Alert(type);

        window.initOwner(main.getPrimaryStage());
        window.setResizable(true);
        window.getDialogPane().setPrefHeight(250);
        window.setTitle(Main.APP_NAME);
        window.setHeaderText(header);
        window.setContentText(text);

        window.showAndWait();
    }

    private void showHandedDocumentDetails(ViewableHandedDocument handedDocument) {
        if (handedDocument != null) {
            serialLabel.setText(handedDocument.getSerial());
            descriptionLabel.setText(handedDocument.getDescription());
            departmentLabel.setText(handedDocument.getDepartment().name());
            nameLabel.setText(handedDocument.getNameHanded());
            handedDateLabel.setText(DateUtil.format(handedDocument.getHandedDate()));
            isReturnedLabel.setText(handedDocument.isReturned() ? "SÍ" : "NO");
            returnedDateLabel.setText(handedDocument.getReturnedDate() != null ?
                    DateUtil.format(handedDocument.getReturnedDate()) : "NO HAY");
        } else {
            serialLabel.setText("---");
            descriptionLabel.setText("---");
            departmentLabel.setText("---");
            nameLabel.setText("---");
            handedDateLabel.setText("---");
            isReturnedLabel.setText("---");
            returnedDateLabel.setText("---");
        }
    }
}
