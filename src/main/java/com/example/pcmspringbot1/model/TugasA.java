package com.example.pcmspringbot1.model;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on 17/02/2025 21:29
@Last Modified 17/02/2025 21:29
Version 1.0
*/
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "MstTugasA ")
public class TugasA {

    @Id
    @Column(name = "IDTugasA")
    private String idTugasA;


    @Column(name = "Nama",length = 40,nullable = false)
    private String nama;

    @Column(name = "Alamat",length = 500,nullable = false)
    private String alamat;

    @Column(name = "TanggalLahir",nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "JenisKelamin",nullable = false)
    private Character jenisKelamin;

    @Column(name = "MasihHidup",nullable = false)
    private Boolean masihHidup;

    @Column(name = "CreatedDate",nullable = false,columnDefinition = "datetime2(7)")
    private Date createdDate;

    @Column(name = "CreatedBy",nullable = false)
    private Integer createdBy;

    @Column(name = "ModifiedDate",columnDefinition = "datetime2(7)")
    private Date modifiedDate;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete",nullable = false)
    private Short isDelete;
}