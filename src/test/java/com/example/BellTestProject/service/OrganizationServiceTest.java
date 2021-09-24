package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.OrganizationDAO;
import com.example.BellTestProject.model.Organization;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;


class OrganizationServiceTest {

    @Autowired
    Organization organization;
    @Mock
    OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
    @Mock
    OrganizationService organizationService = Mockito.mock(OrganizationService.class);

    @Test
    void check_findAllOrganizationByName_Should_Return_All_OrganizationByName(){
        List<Organization> organizations = new ArrayList<>();
        when(organizationDAO.getByName("xxx")).thenReturn(organizations);
    }

    @Test
    void check_saveOrganization_Should_Works(){
        organizationService.saveOrganization(organization);
        Mockito.verify(organizationService).saveOrganization(organization);
    }

    @Test
    void check_getById_Should_OrganizationById(){
        when(organizationDAO.getById(2)).thenReturn(organization);
    }

    @Test
    void check_updateOrganization_Should_Works(){
        organizationService.updateOrganization(organization);
        Mockito.verify(organizationService).updateOrganization(organization);
    }

}