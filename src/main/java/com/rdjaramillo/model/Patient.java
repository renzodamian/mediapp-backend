package com.rdjaramillo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_patient")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPatient;

    @Column(length=70, nullable = false)
    private String firstName;

    @Column(length=70, nullable = false)
    private String lastName;

    @Column(length=10, nullable = false)
    private String dni;

    @Column(length=150, nullable = false)
    private String address;

    @Column(length=10, nullable = false)
    private String phone;

    @Column(length=70, nullable = false)
    private String email;



}

