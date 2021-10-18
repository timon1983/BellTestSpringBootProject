package com.example.BellTestProject.integration_tests;

import com.example.BellTestProject.controller.AuthenticationRestController;
import com.example.BellTestProject.dao.OrganizationDAO;
import com.example.BellTestProject.dto.AuthenticationRequestDTO;
import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.view.ResponseViewData;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** Интеграционный тест ,проверка записи сущности organization в БД , затем
 * сравнивнение данных сущности после ее извлечения . При проверке используется
 * база данных h2 , конфигурация в файле application-h2.properties
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(value = "h2" )
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTestForOrganization {

    @Autowired
    Organization organization;
    private RestTemplate restTemplate;
    private String token;


    /** Получение JWT токена*/
    @BeforeAll
    public void getAuth() throws URISyntaxException {

        restTemplate = new RestTemplate();
        String URLAuth = "http://localhost:8088/api/auth/login";
        URI uriAuth = new URI(URLAuth);
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
        authenticationRequestDTO.setEmail("admin@gmail.com");
        authenticationRequestDTO.setPassword("admin");
        ResponseEntity<Map> response = restTemplate.postForEntity(uriAuth , authenticationRequestDTO , Map.class);
        Map<Object , Object> map =  response.getBody();
        token = (String) map.get("token");
        System.out.println(token);
    }

    /** Запись сущности в БД , получение данных из БД , сравнение результатов*/
    @Test
    public void createOrganizationAndSaveOrganization() throws URISyntaxException {

        String URLSave = "http://localhost:8088/api/organization/save";
        URI uriSave = new URI(URLSave);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization" , token);
        organization.setId(1);
        organization.setName("Bell");
        organization.setFullName("BellIntegrator");
        organization.setInn(1111);
        organization.setKpp(22222);
        organization.setAddress("Sverdlov92");
        organization.setPhone(333);
        HttpEntity<Organization> request = new HttpEntity<>(organization, headers);
        System.out.println(request);
        restTemplate.postForEntity(uriSave , request , Map.class);

        String URLGet = "http://localhost:8088/api/organization/1";
        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(null, headers);
        ResponseEntity<ResponseViewData> responseEntity = restTemplate.exchange(URLGet , HttpMethod.GET, request2 , ResponseViewData.class);
        ResponseViewData responseViewData = responseEntity.getBody();
        Map<String , String> orgMap = (Map<String, String>) responseViewData.getData();

        assertNotNull(orgMap);
        assertEquals("Bell", orgMap.get("name"));
        assertEquals("BellIntegrator", orgMap.get("fullName"));
        assertEquals(1111, orgMap.get("inn"));
        assertEquals(22222, orgMap.get("kpp"));
        assertEquals("Sverdlov92", orgMap.get("address"));
        assertEquals(333, orgMap.get("phone"));
    }
}
