package com.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.UserService;
import com.api.model.User;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService  userService;

    @Autowired
    public UserController( UserService userService  ){
        this.userService= userService;

    }

    //obtener tdos los usuarios
     @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
        }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserId(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK); // Devuelve 200 OK y el usuario encontrado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si no se encuentra el usuario
        }
    }

     // Guardar un nuevo usuario
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // Devuelve 201 Created y el usuario creado
    }

     // Editar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.getUserId(id);
        if (user != null) {
            updatedUser.setId(id); // Aseguramos que el ID del usuario coincida con el ID proporcionado en la URL
            User savedUser = userService.saveUser(updatedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK); // Devuelve 200 OK y el usuario actualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si no se encuentra el usuario
        }
    }

     // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.getUserId(id);
        if (user != null) {
            userService.deletUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 No Content si el usuario fue eliminado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si no se encuentra el usuario
        }
    }

    // Buscar usuarios por palabra clave
    @GetMapping("/search")
    public List<User> searchUsersByKeyword(@RequestParam("keyword") String keyword) {
        return userService.searchUsersByKeyword(keyword);
    }
}
