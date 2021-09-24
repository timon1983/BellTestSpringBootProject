package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.OfficeDAO;
import com.example.BellTestProject.model.Office;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class OfficeServiceTest {

    @Autowired
    Office office;
    @Mock
    OfficeDAO officeDAO = Mockito.mock(OfficeDAO.class);
    @Mock
    OfficeService officeService = Mockito.mock(OfficeService.class);

    @Test
    void check_getAllOfficesByOrganizationId_Should_Return_AllOfficesByOrganizationId(){
        List<Office> offices = new ArrayList<>();
        when(officeDAO.getByOrganizationId(2)).thenReturn(offices);
    }

    @Test
    void check_saveOffice_Should_Works(){
        officeService.saveOffice(office);
        Mockito.verify(officeService).saveOffice(office);
    }

    @Test
    void check_getOfficeById_Should_Return_OfficeById(){
        when(officeDAO.getById(2)).thenReturn(office);
    }

    @Test
    void check_updateOffice_Should_Works(){
        officeService.updateOffice(office);
        Mockito.verify(officeService).updateOffice(office);
    }

}