package com.example.pcmspringbot1.model;


import jakarta.persistence.*;

@Entity
@Table(name = "TrxDataCoba")
public class DataCoba {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "Duit")
    private Float duit;

    @Column(name = "Nomor")
    private Integer nomor;

    @Column(name = "Desimal")
    private Double desimal;

    @Column(name = "NamaDepan")
    private String namaDepan;

    @Column(name = "NamaTengah")
    private String namaTengah;

    @Column(name = "NamaBelakang")
    private String namaBelakang;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "Kota")
    private String kota;

    @Column(name = "Alamat")
    private String alamat;

    @Column(name = "NoHp")
    private String NoHp;



















}
