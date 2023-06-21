package com.rentstate.bckendrentstate.rentstate.domain.persistence;

import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByAge(int age);

    User findByName(String name);
    User findByEmail(String email);
}
