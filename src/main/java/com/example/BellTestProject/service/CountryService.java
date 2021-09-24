package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.CountryDAO;
import com.example.BellTestProject.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    private CountryDAO countryDAO;

    @Autowired
    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Transactional
    public List<Country> getAllCountries(){
        return countryDAO.getAll();
    }
}
