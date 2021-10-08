package com.example.BellTestProject.controller;

import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.model.Document;
import com.example.BellTestProject.service.DocumentService;
import com.example.BellTestProject.view.ResponseViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/docs")
public class DocumentController {

    private DocumentService documentService;
    private ResponseViewData responseViewData;

    @Autowired
    public DocumentController(DocumentService documentService, ResponseViewData responseViewData) {
        this.documentService = documentService;
        this.responseViewData = responseViewData;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('documents:read')")
    public ResponseEntity<ResponseViewData> getAllDocs(){
        HttpHeaders headers = new HttpHeaders();
        List<Document> documents = documentService.getAllDocuments();
        if(documents.isEmpty()){
            throw new NoSuchDataException("Список документов пуст");
        }
        responseViewData.setData(documents);
        return  new ResponseEntity<>(responseViewData, headers, HttpStatus.OK);
    }
}
