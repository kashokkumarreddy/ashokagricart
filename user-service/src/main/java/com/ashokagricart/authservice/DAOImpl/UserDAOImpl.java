package com.ashokagricart.authservice.DAOImpl;

import com.ashokagricart.authservice.DAO.UserDAO;
import com.ashokagricart.authservice.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user){
        if(user.getId() == null){
            entityManager.persist(user);
        }else{
            entityManager.merge(user);
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email",User.class
        );
        query.setParameter("email",email);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public boolean existByEmail(String email) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u WHERE email = :email",Long.class
        );
        query.setParameter("email",email);
        return query.getSingleResult() > 0;
    }
}
