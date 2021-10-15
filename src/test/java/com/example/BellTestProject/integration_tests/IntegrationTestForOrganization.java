package com.example.BellTestProject.integration_tests;

import com.example.BellTestProject.controller.AuthenticationRestController;
import com.example.BellTestProject.controller.OrganizationController;
import com.example.BellTestProject.dao.OrganizationDAO;
import com.example.BellTestProject.dto.AuthenticationRequestDTO;
import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.view.ResponseViewData;
import io.jsonwebtoken.Header;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** интеграционный тест ,проверяем запись сущности organization ,
 * сравниваем данные сущности после ее извлечения . При проверке используем
 * базу h2 , конфигурация в файле application-h2.properties
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(value = "h2" )
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTestForOrganization {


    @Autowired
    OrganizationController organizationController;
    @Autowired
    OrganizationDAO organizationDAO;
    @Autowired
    Organization organization;
    @Autowired
    AuthenticationRestController authenticationRestController;


    private String token;




    @BeforeAll
    public void getAuth(){
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
        authenticationRequestDTO.setEmail("admin@gmail.com");
        authenticationRequestDTO.setPassword("admin");
        ResponseEntity<Map<Object , Object>> response = (ResponseEntity<Map<Object, Object>>) authenticationRestController.authenticate(authenticationRequestDTO);
        Map<Object , Object> map =  response.getBody();
        token = (String) map.get("token");
        System.out.println(token);
    }

    @Test
    public void createOrganizationAndSaveOrganization() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8088/api/organization/save";
        URI uri = new URI(URL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization" , token);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("id" , "1");
        params.add("name" , "Bell");
        params.add("fullName" , "BellIntegrator");
        params.add("inn" , "1111");
        params.add("kpp" , "22222");
        params.add("address" , "Sverdlov92");
        params.add("phone" , "333");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        System.out.println(request);
        restTemplate.exchange(uri , HttpMethod.POST ,  request , ResponseEntity.class );
//        organization.setName("Bell");
//        organization.setFullName("BellIntegrator");
//        organization.setInn(1111);
//        organization.setKpp(22222);
//        organization.setAddress("Sverdlov92");
//        organization.setPhone(333);
//        System.out.println(organization);

//        organizationController.saveOrganization(organization);


        String URL2 = "localhost:8088/api/organization/1";
        URI uri2 = new URI(URL2);
        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(null, headers);
        ResponseEntity organizationEntity = restTemplate.exchange(uri2 , HttpMethod.GET, request2 , ResponseEntity.class);
        ResponseViewData responseViewData = (ResponseViewData) organizationEntity.getBody();
        organization = (Organization) responseViewData.getData();
        System.out.println(organization);
        assertNotNull(organization);
        assertEquals("Bell", organization.getName());
        assertEquals("BellIntegrator", organization.getFullName());
        assertEquals(1111, organization.getInn());
        assertEquals(22222, organization.getKpp());
        assertEquals("Sverdlov92", organization.getAddress());
        assertEquals(333, organization.getPhone());
    }
}
