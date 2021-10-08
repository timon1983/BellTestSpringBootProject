package com.example.BellTestProject.controller;

import com.example.BellTestProject.dao.UserAuthDAOImpl;
import com.example.BellTestProject.dto.AuthenticationRequestDTO;
import com.example.BellTestProject.model.UserAuth;
import com.example.BellTestProject.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private UserAuthDAOImpl userAuthDAO;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserAuthDAOImpl userAuthDAO, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userAuthDAO = userAuthDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**При запросе аутентифицируем пользователя на основании email и password ,
     * получаем его для получения его роли, создаем токен на основании
     * полученных данных о пользователе ,при успешном создании в ответе возвращаем email и токен
     * */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request){
        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(request.getEmail() , request.getPassword())));
            UserAuth userAuth = userAuthDAO.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Такого UserAuth не существует"));
            String token =jwtTokenProvider.createToken(request.getEmail() , userAuth.getRole().name());
            Map<Object , Object> response = new HashMap<>();
            response.put("email" , request.getEmail());
            response.put("token" , token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e){
            return new ResponseEntity<>("Некоректная комбинация email/password" , HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

