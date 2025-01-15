package com.example.pcmspringbot1.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "MstAkses")
public class Akses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAkses")
    private Long id;

    @Column(name = "NamaAkses", nullable = false, unique = true,length = 40)
    private String nama;

    @ManyToMany
    @JoinTable(name = "MapAksesMenu",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique-akses-menu",
                        columnNames = {"IDAkses","IDMenu"})},
        joinColumns = @JoinColumn(name = "IDAkses", foreignKey = @ForeignKey(name = "fk-to-map-akses")),
        inverseJoinColumns = @JoinColumn(name = "IDMenu", foreignKey = @ForeignKey(name = "fk-to-map-menu"))
    )
    private List<Menu> ltMenu;

    @Column(name = "CreatedBy",updatable = false,nullable = false)
    private String createdBy;
    @Column(name = "CreatedDate",updatable = false,nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy",insertable = false)
    private String updatedBy;
    @Column(name = "UpdatedDate",insertable = false)
    private Date updatedDate;

    public List<Menu> getLtMenu() {
        return ltMenu;
    }

    public void setLtMenu(List<Menu> ltMenu) {
        this.ltMenu = ltMenu;
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
