package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.DocumentDAO;
import com.example.BellTestProject.model.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class DocumentServiceTest {

    @Mock
    DocumentDAO documentDAO = Mockito.mock(DocumentDAO.class);

    @Test
    void check_getAllDocuments_Should_Return_AllDocuments(){
        List<Document> documents = new ArrayList<>();
        when(documentDAO.getAll()).thenReturn(documents);
    }

}