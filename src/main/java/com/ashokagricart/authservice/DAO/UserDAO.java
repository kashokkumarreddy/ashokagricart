package com.ashokagricart.authservice.DAO;

import com.ashokagricart.authservice.model.User;
import java.util.Optional;


public interface UserDAO {
    User save(User user);
    Optional<User> findByEmail(String Email);
    boolean existByEmail(String email);
}
