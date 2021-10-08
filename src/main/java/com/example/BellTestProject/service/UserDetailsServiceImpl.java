package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.UserAuthDAOImpl;
import com.example.BellTestProject.dto.UserSecurity;
import com.example.BellTestProject.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserAuthDAOImpl userAuthDAO;

    @Autowired
    public UserDetailsServiceImpl(UserAuthDAOImpl userAuthDAO) {
        this.userAuthDAO = userAuthDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthDAO.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("UserAuth does not exists"));
        return UserSecurity.fromUserAuth(userAuth);
    }
}
