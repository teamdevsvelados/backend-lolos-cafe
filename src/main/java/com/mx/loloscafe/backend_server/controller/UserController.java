package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.UserNotFoundException;
import com.mx.loloscafe.backend_server.model.User;
import com.mx.loloscafe.backend_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Se hace un "mapeo explicito" en el Request Mapping
@RequestMapping("/api/v1/users") //"/api" es la convención tradicional para trabajar con APIs

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping //Mapear es identificarlos
    public List<User> getAllUsers(){
        return userService.getUsers();
        //El objetivo de getUsers es mapear la acción del metodo
    }

    //=====Mapear la creación de nuevas instancias========

    @PostMapping("/new-user")
    public ResponseEntity<User> saveUser (@RequestBody User newUser) {
        // Response nos permite saber el estado de lo que ocurrió con nuestra solicitud

        User userByEmail = userService.findByEmail(newUser.getEmail());
        // Se crea el método Para poder usar el condicional que determine si existe
        //  o no el usuario que se va a crear antes de hacerlo

        if (userByEmail != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
            //Http responde que "Ya está en uso el correo"
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser));
            //Http responde que "Se creó" y lo crea a partir de lo que
            // creo que le dijeron que creara
        }
    }
    //Vamos a mapear encontrar a un usuario, los codigos serán 200 y 404

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id){ //Pathvariable permite encontrar por medio
            // del URL (Endpoint) la variable
            try {
                return ResponseEntity.ok( userService.findById(id));

            } catch (UserNotFoundException Ex){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    // ======== Mapear eliminar usuario por ID (deleteUserById)==========

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        try{ userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException Ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Creación de Update User
    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> UpdateUser(@RequestBody User user, @PathVariable Integer id){ //este es el cuerpo para que retorne ese estado
        try { return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserById(user, id));
        } catch (UserNotFoundException Ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    }