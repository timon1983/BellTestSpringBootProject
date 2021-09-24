package com.example.BellTestProject.configuration;

import com.example.BellTestProject.model.Office;
import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.model.User;
import com.example.BellTestProject.view.ResponseViewData;
import com.example.BellTestProject.view.ResponseViewSuccess;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;

@Configuration
public class ModelConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Organization getOrganization(){
        return new Organization();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Office getOffice(){
        return new Office();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User getUser(){
        return new User();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public HttpHeaders getHttpHeaders(){
        return new HttpHeaders();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ResponseViewSuccess getResponseView(){
        return new ResponseViewSuccess("success");
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ResponseViewData getResponseData(){
        return new ResponseViewData();
    }
}
