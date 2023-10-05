package com.ndhphuc.motngaythu6.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;

    private int isBlock;

    private Date createDate = new Date();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }
}
