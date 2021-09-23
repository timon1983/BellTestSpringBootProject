package com.example.BellTestProject.DAO.impl;

import com.example.BellTestProject.DAO.CountryDAO;
import com.example.BellTestProject.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO {

    private final EntityManager em;

    @Autowired
    public CountryDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Country> getAll() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c" , Country.class);
        return query.getResultList();
    }
}
