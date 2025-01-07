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

import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MstPeserta",indexes = {
        @Index(name = "idx-cc",columnList = "nama")
})
public class Peserta {

    @Id
    @Column(name = "IDPeserta")
    private Long id;

    @Column(name = "NamaPeserta",insertable = false,length = 50,unique = true)
    private String nama;

    private String alamat;

    @Column(name = "CreatedBy",updatable = false,nullable = false)
    private String createdBy;
    @Column(name = "CreatedDate",updatable = false,nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy",insertable = false)
    private String updatedBy;
    @Column(name = "UpdatedDate",insertable = false)
    private Date updatedDate;

    /** RUBAH SAAT MIGRASI */
    @Column(name = "Duit",columnDefinition = "decimal(5,3)")
    private float duit;

    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;

    @Transient
    private Integer umur;

    public float getDuit() {
        return duit;
    }

    public void setDuit(float duit) {
        this.duit = duit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
