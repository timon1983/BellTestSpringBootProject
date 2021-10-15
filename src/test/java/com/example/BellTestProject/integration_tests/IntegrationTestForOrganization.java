package com.example.BellTestProject.integration_tests;

import com.example.BellTestProject.controller.OrganizationController;
import com.example.BellTestProject.dao.OrganizationDAO;
import com.example.BellTestProject.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** интеграционный тест ,проверяем запись сущности organization ,
 * сравниваем данные сущности после ее извлечения . При проверке используем
 * базу h2 , конфигурация в файле application-h2.properties
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("h2")
@Transactional
public class IntegrationTestForOrganization {


    OrganizationController organizationController;

    OrganizationDAO organizationDAO;

    Organization organization;

    @Autowired
    public IntegrationTestForOrganization(OrganizationController organizationController, OrganizationDAO organizationDAO, Organization organization) {
        this.organizationController = organizationController;
        this.organizationDAO = organizationDAO;
        this.organization = organization;
    }

    @Test
    public void createOrganizationAndSaveOrganization(){

        organization.setName("Bell");
        organization.setFullName("BellIntegrator");
        organization.setInn(1111);
        organization.setKpp(22222);
        organization.setAddress("Sverdlov92");
        organization.setPhone(333);
        System.out.println(organization);
        organizationController.saveOrganization(organization);
        System.out.println(organization);

        Organization organization1 = organizationDAO.getById(organization.getId());

        assertNotNull(organization1);
        assertEquals("Bell", organization1.getName());
        assertEquals("BellIntegrator", organization1.getFullName());
        assertEquals(1111, organization1.getInn());
        assertEquals(22222, organization1.getKpp());
        assertEquals("Sverdlov92", organization1.getAddress());
        assertEquals(333, organization1.getPhone());
    }
}
