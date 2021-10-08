package com.example.BellTestProject.dao;

import com.example.BellTestProject.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAuthDAOImpl extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByEmail(String email);
}
