package com.khoders.geez.entities.auth;
import com.khoders.geez.entities.constants.UserRole;
import com.khoders.springapi.spring.SpringBaseModel;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends SpringBaseModel {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private UserRole roleName;

    public UserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRole roleName) {
        this.roleName = roleName;
    }
}
