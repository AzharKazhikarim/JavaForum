package net.javaForum.javaForum.repository;

import net.javaForum.javaForum.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepo extends JpaRepository<Ad, Long> {

}
