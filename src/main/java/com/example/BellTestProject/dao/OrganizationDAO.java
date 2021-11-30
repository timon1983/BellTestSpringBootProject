package com.example.BellTestProject.dao;

import com.example.BellTestProject.model.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrganizationDAO {

    List<Organization> getByName(String name);

    Organization getById(int id);

    Organization update(Organization organization);

    void save(Organization organization);
}
