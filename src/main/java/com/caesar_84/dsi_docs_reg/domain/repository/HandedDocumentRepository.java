package com.caesar_84.dsi_docs_reg.domain.repository;

import com.caesar_84.dsi_docs_reg.domain.HibernateUtil;
import com.caesar_84.dsi_docs_reg.domain.model.HandedDocument;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HandedDocumentRepository {
    private HandedDocumentRepository() {}

    private static HandedDocumentRepository instance = null;

    public HandedDocument get(int id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM HandedDocument d WHERE d.id = :id");
        query.setParameter("id", id);
        HandedDocument document = (HandedDocument) query.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return document;
    }

    public void save(HandedDocument handedDocument) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.save(handedDocument);
        session.getTransaction().commit();
        session.close();
    }

    public void update(HandedDocument handedDocument) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.update(handedDocument);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(int id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        HandedDocument document = session.load(HandedDocument.class, id);
        session.delete(document);
        session.getTransaction().commit();
        session.close();

    }

    @SuppressWarnings(value = "unchecked")
    public List<HandedDocument> getAll() {
        List<HandedDocument> list = new ArrayList<>();

        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        list = session.createQuery("FROM HandedDocument d ORDER BY d.handedDate DESC").list();
        session.getTransaction().commit();
        session.close();

        return list;
    }

    public static HandedDocumentRepository getInstance() {
        if (instance == null) {
            instance = new HandedDocumentRepository();
        }

        return instance;
    }
}
