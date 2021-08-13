package dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
