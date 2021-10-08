package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.OfficeDAO;
import com.example.BellTestProject.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OfficeService {

    private OfficeDAO officeDAO;

    @Autowired
    public OfficeService(OfficeDAO officeDAO) {
        this.officeDAO = officeDAO;
    }

    @Transactional
    public List<Office> getAllOfficesByOrganizationId(int id){
        return officeDAO.getByOrganizationId(id);
    }

    @Transactional
    public void saveOffice(Office office){
        officeDAO.save(office);
    }

    @Transactional(readOnly = true)
    public Office getOfficeById(int id){
        return officeDAO.getById(id);
    }

    @Transactional
    public void updateOffice(Office office){
        officeDAO.update(office);
    }
}
