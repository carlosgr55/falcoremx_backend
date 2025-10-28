package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    public User findByUsernameAndPassword(String username, String password);

    public List<User> findByIdEmpresa(Integer idEmpresa);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = ?2 WHERE u.username = ?1")
    void updatePasswordByUsername(String username, String password);
}
