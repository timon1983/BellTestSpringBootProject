package com.example.BellTestProject.controller;

import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.model.Country;
import com.example.BellTestProject.service.CountryService;
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
@RequestMapping("api/country")
public class CountryController {

    private CountryService countryService;
    private ResponseViewData responseViewData;

    @Autowired
    public CountryController(CountryService countryService, ResponseViewData responseViewData) {
        this.countryService = countryService;
        this.responseViewData = responseViewData;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('countries:read')")
    public ResponseEntity<ResponseViewData> getAllCountries(){
        HttpHeaders headers = new HttpHeaders();
        List<Country> countries = countryService.getAllCountries();
        if(countries.isEmpty()){
            throw new NoSuchDataException("Список стран пуст");
        }
        responseViewData.setData(countries);
        return new ResponseEntity<>(responseViewData, headers, HttpStatus.OK);
    }
}
