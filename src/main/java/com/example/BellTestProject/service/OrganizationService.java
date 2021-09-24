package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.OrganizationDAO;
import com.example.BellTestProject.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationService {

    private OrganizationDAO organizationDAO;

    @Autowired
    public OrganizationService(OrganizationDAO organizationDAO) {
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

    @Transactional
    public Organization getById(int id){
        return organizationDAO.getById(id);
    }

    @Transactional
    public void updateOrganization(Organization organization) {
        organizationDAO.update(organization);
    }
}
