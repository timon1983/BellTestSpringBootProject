package com.example.BellTestProject.DAO.impl;

import com.example.BellTestProject.DAO.DocumentDAO;
import com.example.BellTestProject.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {

    private final EntityManager em;

    @Autowired
    public DocumentDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Document> getAll() {
        TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d" , Document.class);
        return query.getResultList();
    }
}
