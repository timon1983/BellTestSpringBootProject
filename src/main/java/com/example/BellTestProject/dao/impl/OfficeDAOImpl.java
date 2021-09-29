package com.example.BellTestProject.dao.impl;

import com.example.BellTestProject.dao.OfficeDAO;
import com.example.BellTestProject.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> getByOrganizationId(int orgId) {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o WHERE o.organization.id =" +
                " " + orgId, Office.class);
        return query.getResultList();
    }

    @Override
    public Office getById(int id) {
        return em.find(Office.class, id);
    }

    @Override
    public Office update(Office office) {
        Office office1 = getById(office.getId());
        office.setOrganization(office1.getOrganization());
        return em.merge(office);
    }

    @Override
    public void save(Office office) {
        em.merge(office);
    }
}
