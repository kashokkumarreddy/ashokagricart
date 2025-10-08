package com.ashokagricart.authservice.serviceImpl;

import com.ashokagricart.authservice.DAO.UserDAO;
import com.ashokagricart.authservice.dto.LoginRequestDTO;
import com.ashokagricart.authservice.dto.LoginResponseDTO;
import com.ashokagricart.authservice.exception.EmailNotVerifiedException;
import com.ashokagricart.authservice.service.LoginService;
import com.ashokagricart.authservice.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDAO userDAO;

    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){

        var user  = userDAO.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow( () -> new RuntimeException("Invalid email or password"));
        if(!user.isEnabled()){
            throw new EmailNotVerifiedException("Email verification is required");
        }

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getEmail(),
                            loginRequestDTO.getPassword()
                    )
            );
            String token = jwtUtil.generateToken(loginRequestDTO.getEmail());
            return new LoginResponseDTO(token);
        } catch(BadCredentialsException ex ){
            throw new BadCredentialsException("Invalid Credentials");
        }
    }

}
