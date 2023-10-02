package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoleRepository extends JpaRepository<Role, BigInteger> {

    @Query(value = "select r.* from roles r where r.userId = :userId",nativeQuery = true)
    Role findByUserId(BigInteger userId);
}
