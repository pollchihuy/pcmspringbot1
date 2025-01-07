package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.Produk;
import org.springframework.data.repository.CrudRepository;

public interface ProdukRepo extends CrudRepository<Produk, Long> {
}
