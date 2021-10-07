package com.example.BellTestProject.controller;

import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.service.OrganizationService;
import com.example.BellTestProject.view.ResponseViewData;
import com.example.BellTestProject.view.ResponseViewSuccess;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/organization")

@Tag(name = "Организации", description = "Контроллер для операций над организациями")
public class OrganizationController {

    private OrganizationService organizationService;



    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Lookup
    public HttpHeaders getHeaders(){

        return null;
    }
    @Lookup
    public ResponseViewData getResponseData(){

        return null;
    }
    @Lookup
    public ResponseViewSuccess getResponseView(){

        return null;
    }

    @PostMapping("/list")
    @Operation(
            summary = "Список организаций",
            description = "Позволяет получить список организаций по имени"
    )
    public ResponseEntity<ResponseViewData> getAllOrganizationByName(@RequestBody Organization organization){
        List<Organization> organizations = organizationService.findAllOrganizationByName(organization.getName());
        if(organizations.isEmpty()){
            throw new NoSuchDataException("Нет организации с именем = " + organization.getName());
        }
        ResponseViewData responseViewData = getResponseData();
        responseViewData.setData(organizations);
        return new ResponseEntity<>(responseViewData,getHeaders(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseViewData> saveOrganization(@RequestBody Organization organization) {
        organizationService.saveOrganization(organization);
        ResponseViewData responseViewData = getResponseData();
        responseViewData.setData(getResponseView());
        return new ResponseEntity<>(responseViewData,getHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseViewData> getOrganizationById(@PathVariable("id") @Parameter(description = "Идентификатор организации в БД") int id){
        Organization organization = organizationService.getById(id);
        if(organization == null){
            throw new NoSuchDataException("Нет организации с ID = " + id);
        }
        ResponseViewData responseViewData = getResponseData();
        responseViewData.setData(organization);
        return new ResponseEntity<>(responseViewData, getHeaders(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseViewData> updateOrganization(@RequestBody Organization organization) {
        Organization organization1 = organizationService.getById(organization.getId());
        if(organization1 == null){
            throw new NoSuchDataException("Нет организации с ID = " + organization.getId());
        }else {
            organizationService.updateOrganization(organization);
        }
        ResponseViewData responseViewData = getResponseData();
        responseViewData.setData(getResponseView());
        return new ResponseEntity<>(responseViewData,getHeaders(), HttpStatus.OK);
    }
}

