package com.example.pcmspringbot1.model;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 21:28
@Last Modified Fri 21:28
Version 1.0
*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MstPeserta")
public class Peserta {

    @Id
    @Column(name = "IDPeserta")
    private Long id;

    @Column(name = "NamaPeserta",insertable = false)
    private String nama;

    private String alamat;


}
