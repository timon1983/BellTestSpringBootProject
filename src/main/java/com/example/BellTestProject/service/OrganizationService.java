package com.example.BellTestProject.service;

import com.example.BellTestProject.DAO.OrganizationDAO;
import com.example.BellTestProject.DAO.impl.OrganizationDAOImpl;
import com.example.BellTestProject.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationService {

    private OrganizationDAOImpl organizationDAO;

    @Autowired
    public OrganizationService(OrganizationDAOImpl organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @Transactional
    public List<Organization> findAllOrganizationByName(String name){
        return organizationDAO.getByName(name);
    }

    @Transactional
    public void saveOrganization(Organization organization){
        organizationDAO.save(organization);
    }
}
