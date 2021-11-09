package net.javaForum.javaForum.repository;


import net.javaForum.javaForum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    User getByUsername(String username);
    @Transactional
    void deleteByUsername(String email);
}
