package com.example.pcmspringbot1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MstProduk")
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProduk")
    private Long id;

    @Column(name = "NamaProduk",unique = true, nullable = false)
    private String nama;

    @ManyToOne
    @JoinColumn(name = "IDKategoriProduk", foreignKey = @ForeignKey(name = "fk-to-kategoriprod"))
    private KategoriProduk kategoriProduk;

    @ManyToMany(mappedBy = "produks")
    private List<Supplier> suppliers;

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public KategoriProduk getKategoriProduk() {
        return kategoriProduk;
    }

    public void setKategoriProduk(KategoriProduk kategoriProduk) {
        this.kategoriProduk = kategoriProduk;
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
}
