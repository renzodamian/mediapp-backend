package com.rdjaramillo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(nullable = false , length = 70, unique = true)
    private String username;

    @Column(nullable = false , length = 60)
    private String password;

    @Column(nullable = false)
    private boolean enable;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="tbl_user_role",
                joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
                inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<Role> roles;
}
