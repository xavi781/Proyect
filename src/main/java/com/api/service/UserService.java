package com.api.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import com.api.model.User;
import com.api.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository= userRepository;

    }

    //obtener todos los usuarios
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //obtener usuario por id 
    public User getUserId(Long id){
        return userRepository.findById(id).orElse(null) ;
    }

    //metodo guardar usuario 
    public User  saveUser(User user){
        return userRepository.save(user);
    }

    //metodo eliminar usuario por id
    @Secured("ROLE_CUSTOMER")
    public void deletUser(Long id){
         userRepository.deleteById(id);
    }

    public List<User> searchUsersByKeyword( String keyword){
        return userRepository.searchByKeyword(keyword);
    }
}
