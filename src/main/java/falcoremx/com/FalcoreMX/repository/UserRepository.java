package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUsernameAndPassword(String username, String password);

    public List<User> findByIdEmpresa(Integer idEmpresa);


}
