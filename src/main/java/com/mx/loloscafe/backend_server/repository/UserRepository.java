package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    // Se extiende JPARepository para traer a user repository todo sus métodos
    // Los métodos que nos interesan para esto son los asociados a CRUD
    // saveById, findById, deleteById

    //User findByEmail(String email); //OLD

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

}


