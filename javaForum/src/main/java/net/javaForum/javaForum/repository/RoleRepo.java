package net.javaForum.javaForum.repository;

import net.javaForum.javaForum.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String role_name);
}
