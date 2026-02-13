package com.mx.loloscafe.backend_server.service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.mx.loloscafe.backend_server.exceptions.UserNotFoundException;
import com.mx.loloscafe.backend_server.model.User;
import com.mx.loloscafe.backend_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Método para recuperar a todos los usuarios

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //_____Crear nuevas instancias____

    public User createUser(User newUser){
        //Creación del password
        return userRepository.save(newUser);
        // se retorna un newUser, usa el método save para
        // guardarlo y el return muestra que se guardó
    }

    //Método findbyemail adaptado a la lógica de Lolo's Café

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // Método para recuperar usuarios por findByID

    public User findById(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        }
    }





