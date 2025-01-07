package com.example.pcmspringbot1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MstSupplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSupplier")
    private Long id;

    @Column(name = "NamaSupplier",unique = true, nullable = false,length = 25)
    private String nama;

    @ManyToMany
    @JoinTable(name = "MapSupplierProduk",
            uniqueConstraints = {@UniqueConstraint(name = "unq-supplier-produk", columnNames = {"IDProduk","IDSupplier"})},
            joinColumns = @JoinColumn(name = "IDSupplier",foreignKey = @ForeignKey(name = "fk-to-map-supplier")),
            inverseJoinColumns = @JoinColumn(name = "IDProduk", foreignKey = @ForeignKey(name = "fk-to-map-produk"))
    )
    private List<Produk> produks;

    public List<Produk> getProduks() {
        return produks;
    }

    public void setProduks(List<Produk> produks) {
        this.produks = produks;
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
