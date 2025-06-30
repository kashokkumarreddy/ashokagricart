package com.ashokagricart.authservice.serviceImpl;

import com.ashokagricart.authservice.DAO.UserDAO;
import com.ashokagricart.authservice.dto.RegisterRequestDTO;
import com.ashokagricart.authservice.dto.RegisterResponseDTO;
import com.ashokagricart.authservice.model.Role;
import com.ashokagricart.authservice.model.User;
import com.ashokagricart.authservice.service.RegistrationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder){
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO) {
    // check if email already exists
        if(userDAO.existByEmail(registerRequestDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        //Create new User
        User user = new User();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRole(Role.USER);// - Default role
        user.setEnabled(false); // -Email verification is required

        User savedUser = userDAO.save(user);
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setUserId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setStatus("SUCCESS");
        response.setMessage("User registered successfully. Verification email sent");

        return response;
    }
}
