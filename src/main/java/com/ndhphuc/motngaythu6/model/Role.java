package com.ndhphuc.motngaythu6.model;

import com.ndhphuc.motngaythu6.utils.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}
