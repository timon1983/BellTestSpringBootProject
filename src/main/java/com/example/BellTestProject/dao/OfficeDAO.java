package com.example.BellTestProject.dao;

import com.example.BellTestProject.model.Office;

import java.util.List;

public interface OfficeDAO {

    List<Office> getByOrganizationId(int id);

    Office getById(int id);

    Office update(Office office);

    void save(Office office);
}
