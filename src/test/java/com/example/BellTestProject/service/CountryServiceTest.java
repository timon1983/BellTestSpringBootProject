package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.CountryDAO;
import com.example.BellTestProject.model.Country;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

class CountryServiceTest {

    @Mock
    CountryDAO countryDAO = Mockito.mock(CountryDAO.class);

    @Test
    void check_getAllCountries_Should_Return_AllCountries(){
        List<Country> countries = new ArrayList<>();
        when(countryDAO.getAll()).thenReturn(countries);
    }
}