package com.caesar_84.dsi_docs_reg.service;

import com.caesar_84.dsi_docs_reg.domain.model.HandedDocument;
import com.caesar_84.dsi_docs_reg.domain.model.ViewableHandedDocument;
import com.caesar_84.dsi_docs_reg.domain.repository.HandedDocumentRepository;
import com.caesar_84.dsi_docs_reg.util.exceptions.WrongRecordDateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class HandedDocumentService {
    private HandedDocumentService() {
    }

    private static HandedDocumentService instance = null;

    private HandedDocumentRepository repository = HandedDocumentRepository.getInstance();

    public ViewableHandedDocument get(int id) {
        return new ViewableHandedDocument(repository.get(id));
    }

    public void save(ViewableHandedDocument handedDocument) throws WrongRecordDateException {
        if (handedDocument.getReturnedDate() != null) {
            if (handedDocument.getReturnedDate().isBefore(handedDocument.getHandedDate())) {
                throw new WrongRecordDateException();
            }
        }

        repository.save(new HandedDocument(handedDocument));
    }

    public void update(ViewableHandedDocument handedDocument) throws WrongRecordDateException {
        if (handedDocument.getReturnedDate() != null) {
            if (handedDocument.getReturnedDate().isBefore(handedDocument.getHandedDate())) {
                throw new WrongRecordDateException();
            }
        }

        repository.update(new HandedDocument(handedDocument));
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public ObservableList<ViewableHandedDocument> getAll() {
        List<HandedDocument> rawList = repository.getAll();

        return FXCollections.observableArrayList(rawList
                .stream()
                .map(ViewableHandedDocument::new)
                .collect(Collectors.toList()));
    }

    public static HandedDocumentService getInstance() {
        if (instance == null) {
            instance = new HandedDocumentService();
        }

        return instance;
    }
}
