package com.example.BellTestProject.DAO.impl;

import com.example.BellTestProject.DAO.OfficeDAO;
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
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o JOIN FETCH Organization org WHERE o.organization.id =" +
                " " + orgId, Office.class);
        return query.getResultList();
    }

    @Override
    public Office getById(int id) {
        return em.find(Office.class, id);
    }

    @Override
    public Office update(Office office) {
        return em.merge(office);
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }
}
