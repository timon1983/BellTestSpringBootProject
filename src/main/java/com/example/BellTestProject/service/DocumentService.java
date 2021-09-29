package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.DocumentDAO;
import com.example.BellTestProject.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    private DocumentDAO documentDAO;

    @Autowired
    public DocumentService(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Transactional(readOnly = true)
    public List<Document> getAllDocuments(){
        return documentDAO.getAll();
    }
}
