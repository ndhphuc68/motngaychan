package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
    User findByUsername(String username);

    @Query(value = "select u.* from user u join user_roles ur on u.id = ur.user_id join roles r on ur.role_id = r.id where r.name = :role",nativeQuery = true)
    List<User> getListUserByRoles(String role);
}
