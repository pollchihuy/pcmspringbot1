package com.example.pcmspringbot1.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MstGroupMenu")
public class GroupMenu {

    @Id
    @Column(name = "IDGroupMenu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NamaGroupMenu", length = 40, nullable = false, unique = true)
    private String nama;

    @Column(name = "CreatedBy",updatable = false,nullable = false)
    private String createdBy;
    @Column(name = "CreatedDate",updatable = false,nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy",insertable = false)
    private String updatedBy;
    @Column(name = "UpdatedDate",insertable = false)
    private Date updatedDate;

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
}
