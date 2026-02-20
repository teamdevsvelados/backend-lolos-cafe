package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.UserNotFoundException;
import com.mx.loloscafe.backend_server.model.User;
import com.mx.loloscafe.backend_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Método para recuperar a todos los usuarios

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //_____Crear nuevas instancias____

    //register
    public User createUser(User newUser) {
        //Creación del password
        return userRepository.save(newUser);
        // se retorna un newUser, usa el método save para
        // guardarlo y el return muestra que se guardó
    }

    //login
    public User login(String email, String password) {
        return userRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);
    }



//Método findbyemail adaptado a la lógica de Lolo's Café

//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    // Método para recuperar usuarios por findByID

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    //Método para eliminar usuarios por ID

    public void deleteUserById(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else {
            throw new UserNotFoundException(id);
        }
    }
    
    // Método para actualizar un usuario

    public User updateUserById(User user, Integer id){
        return userRepository.findById(id)
                .map(userData -> {
                    userData.setPassword(user.getPassword());
                    userData.setEmail(user.getEmail());
                    userData.setNameOf(user.getNameOf());
                    return userRepository.save(userData);
                })
                .orElseThrow(() -> new UserNotFoundException(id));// Si encuentras lo que vamos a pasarte (id) realiza esto...
    }
}




