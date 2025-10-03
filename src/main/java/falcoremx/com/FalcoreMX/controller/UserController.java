package falcoremx.com.FalcoreMX.controller;


import falcoremx.com.FalcoreMX.entity.User;
import falcoremx.com.FalcoreMX.request.LogInRequest;
import falcoremx.com.FalcoreMX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/falcoremx")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping("/users/empresa/{idEmpresa}")
    public List<User> getUsersByEmpresaId(@PathVariable Integer idEmpresa) {
        return userService.findByIdEmpresa(idEmpresa);
    }

    @GetMapping("/users/login")
    public User loginUser(@RequestBody LogInRequest user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return userService.findByUsernameAndPassword(username, password);
    }

}
