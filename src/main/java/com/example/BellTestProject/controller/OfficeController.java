package com.example.BellTestProject.controller;

import com.example.BellTestProject.dto.OfficeDTO;
import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.model.Office;
import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.service.OfficeService;
import com.example.BellTestProject.view.ResponseViewData;
import com.example.BellTestProject.view.ResponseViewSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/office")
public class OfficeController {

    private OfficeService officeService;
    private Organization organization;
    private Office office;
    private HttpHeaders headers;
    private ResponseViewSuccess responseViewSuccess;
    private ResponseViewData responseViewData;

    @Autowired
    public OfficeController(OfficeService officeService, Organization organization, Office office, HttpHeaders headers,
                            ResponseViewSuccess responseViewSuccess, ResponseViewData responseViewData) {
        this.officeService = officeService;
        this.organization = organization;
        this.office = office;
        this.headers = headers;
        this.responseViewSuccess = responseViewSuccess;
        this.responseViewData = responseViewData;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('offices:read')")
    public ResponseEntity<ResponseViewData> getAllOfficeByOrganizationId(@RequestBody OfficeDTO officeDTO){
        organization.setId(officeDTO.getOrgId());
        office.setOrganization(organization);
        List<Office> offices = officeService.getAllOfficesByOrganizationId(organization.getId());
        if(offices.isEmpty()){
            throw new NoSuchDataException("Нет офисов в организации с ID = " + officeDTO.getOrgId() + " или нет организации с таким ID");
        }
        responseViewData.setData(offices);
        return new ResponseEntity<>(responseViewData,headers,HttpStatus.OK);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('offices:write')")
    public ResponseEntity<ResponseViewData> saveOffice(@RequestBody OfficeDTO officeDTO){
        office.setName(officeDTO.getName());
        office.setAddress( officeDTO.getAddress());
        office.setPhone(officeDTO.getPhone());
        office.setActive(officeDTO.isActive());
        organization.setId(officeDTO.getOrgId());
        office.setOrganization(organization);
        officeService.saveOffice(office);
        responseViewData.setData(responseViewSuccess);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('offices:read')")
    public ResponseEntity<ResponseViewData> getOfficeById(@PathVariable("id") int id){
        Office office = officeService.getOfficeById(id);
        if(office == null){
            throw new NoSuchDataException("Нет офиса с ID = " + id);
        }
        responseViewData.setData(office);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('offices:write')")
    public ResponseEntity<ResponseViewData> updateOffice(@RequestBody Office office){
        Office office1 = officeService.getOfficeById(office.getId());
        if(office1 == null){
            throw new NoSuchDataException("Нет офиса с ID = " + office.getId());
        }else {
            officeService.updateOffice(office);
        }
        responseViewData.setData(responseViewSuccess);
        return new ResponseEntity<>(responseViewData, headers, HttpStatus.OK);
    }
}