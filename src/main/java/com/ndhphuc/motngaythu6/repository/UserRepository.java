package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

    @Query(value = "select u.* from user u where u.username = :username",nativeQuery = true)
    User findByUsername(String username);
}
