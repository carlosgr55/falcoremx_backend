package falcoremx.com.FalcoreMX.service;


import falcoremx.com.FalcoreMX.entity.User;
import falcoremx.com.FalcoreMX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findByIdEmpresa(Integer idEmpresa) {
        return userRepository.findByIdEmpresa(idEmpresa);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}
