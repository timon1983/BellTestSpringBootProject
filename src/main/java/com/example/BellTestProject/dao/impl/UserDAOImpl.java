package com.example.BellTestProject.dao.impl;

import com.example.BellTestProject.dao.UserDAO;
import com.example.BellTestProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> getByOfficeId(int offId) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN FETCH Office o WHERE u.office.id = " + offId , User.class);
        return query.getResultList();
    }

    @Override
    public User getById(int id) {

        return em.find(User.class, id);
    }

    @Override
    public User update(User user) {
        User user1 = getById(user.getId());
        user.setOffice(user1.getOffice());
        return em.merge(user);
    }

    @Override
    public void save(User user) {

        em.persist(user);
    }
}
