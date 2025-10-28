package falcoremx.com.FalcoreMX.controller;


import falcoremx.com.FalcoreMX.entity.User;
import falcoremx.com.FalcoreMX.request.LogInRequest;
import falcoremx.com.FalcoreMX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/falcoremx")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

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
    @PostMapping("/users/login")
    public ResponseEntity<User> loginUser(@RequestBody LogInRequest user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User loggedInUser = userService.findByUsernameAndPassword(username, password);
        if (loggedInUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }

    @PutMapping("/users/password/change/{username}/{newPassword}")
    public void changePassword(@PathVariable String username, @PathVariable String newPassword) {
        userService.changePassword(username, newPassword);
    }

    @PutMapping("/users/resetPassword/{username}")
    public void resetPassword(@PathVariable String username) {
        userService.resetPassword(username);
    }

}
