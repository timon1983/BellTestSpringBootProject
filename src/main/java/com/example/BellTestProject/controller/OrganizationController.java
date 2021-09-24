package com.example.BellTestProject.controller;

import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.service.OrganizationService;
import com.example.BellTestProject.view.ResponseViewData;
import com.example.BellTestProject.view.ResponseViewSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private OrganizationService organizationService;
    private HttpHeaders headers;
    private ResponseViewSuccess responseViewSuccess;
    private ResponseViewData responseViewData;

    @Autowired
    public OrganizationController(OrganizationService organizationService, HttpHeaders headers, ResponseViewSuccess responseViewSuccess , ResponseViewData responseViewData) {
        this.organizationService = organizationService;
        this.headers = headers;
        this.responseViewSuccess = responseViewSuccess;
        this.responseViewData = responseViewData;
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseViewData> getAllOrganizationByName(@RequestBody Organization organization){
        List<Organization> organizations = organizationService.findAllOrganizationByName(organization.getName());
        if(organizations.isEmpty()){
            throw new NoSuchDataException("Нет организации с именем = " + organization.getName());
        }
        responseViewData.setData(organizations);
        return new ResponseEntity<>(responseViewData,headers,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseViewData> saveOrganization(@RequestBody Organization organization) {
        organizationService.saveOrganization(organization);
        responseViewData.setData(responseViewSuccess);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseViewData> getOrganizationById(@PathVariable("id") int id){
        Organization organization = organizationService.getById(id);
        if(organization == null){
            throw new NoSuchDataException("Нет организации с ID = " + id);
        }
        responseViewData.setData(organization);
        return new ResponseEntity<>(responseViewData, headers, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseViewData> updateOrganization(@RequestBody Organization organization) {
        Organization organization1 = organizationService.getById(organization.getId());
        if(organization1 == null){
            throw new NoSuchDataException("Нет организации с ID = " + organization.getId());
        }else {
            organizationService.updateOrganization(organization);
        }
        responseViewData.setData(responseViewSuccess);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }
}

