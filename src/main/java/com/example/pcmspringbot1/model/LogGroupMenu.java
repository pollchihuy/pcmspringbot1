package com.example.pcmspringbot1.model;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Wed 18:23
@Last Modified Wed 18:23
Version 1.0
*/

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "LogGroupMenu")
public class LogGroupMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDGroupMenu")
    private Long idGroupMenu;

    @Column(name = "NamaGroupMenu")
    private String nama;

    @Column(name = "CreatedBy",updatable = false,nullable = false)
    private String createdBy;
    @Column(name = "CreatedDate",updatable = false,nullable = false)
    private Date createdDate = new Date();

    @Column(name = "Flag",length = 1)
    private Character flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGroupMenu() {
        return idGroupMenu;
    }

    public void setIdGroupMenu(Long idGroupMenu) {
        this.idGroupMenu = idGroupMenu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public Character getFlag() {
        return flag;
    }

    public void setFlag(Character flag) {
        this.flag = flag;
    }
}
