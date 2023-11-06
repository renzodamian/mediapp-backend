package com.rdjaramillo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @EqualsAndHashCode.Include
    private Integer idRole;

    @Column(nullable = false , length = 50)
    private String name;

    @Column(nullable = false , length = 100)
    private String description;
}
