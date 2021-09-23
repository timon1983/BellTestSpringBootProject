package com.example.BellTestProject.controller;

import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<Organization>> getAllOrganizationByName(@RequestBody Organization organization){
        HttpHeaders headers = new HttpHeaders();
        if(organization == null){
            return new ResponseEntity<List<Organization>>(HttpStatus.BAD_REQUEST);
        }
        List<Organization> organizations = organizationService.findAllOrganizationByName(organization.getName());

        return new ResponseEntity<List<Organization>>(organizations,headers,HttpStatus.OK);
    }

//    @ApiResponses(value = {
//            @ApiResponse(code = 200 , message = "Success" , response = String.class),
//            @ApiResponse(code = 404, message = "Not Found"),
//            @ApiResponse(code = 500, message = "Failure")
//    })
    @PostMapping("/save")
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization){
        HttpHeaders headers = new HttpHeaders();
        System.out.println(organization);
        organizationService.saveOrganization(organization);
        return new ResponseEntity<Organization>(organization, headers,HttpStatus.OK);

    }
}
