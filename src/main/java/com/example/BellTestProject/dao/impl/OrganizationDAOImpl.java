package com.example.BellTestProject.dao.impl;

import com.example.BellTestProject.model.Organization;
import com.example.BellTestProject.dao.OrganizationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;

//    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> getByName(String name) {
        CriteriaQuery<Organization> criteria = buildCriteria(name);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Organization getById(int id) {
        return em.find(Organization.class, id);
    }

    @Override
    public Organization update(Organization organization) {
        return em.merge(organization);
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    private CriteriaQuery<Organization> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> organizationRoot = criteria.from(Organization.class);
        criteria.where(builder.equal(organizationRoot.get("name"), name));

        return criteria;
    }
}
