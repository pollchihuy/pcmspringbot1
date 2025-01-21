package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuRepo extends JpaRepository<Menu,Long> {

    //DERIVED query
    //select * from MstGroupMenu WHERE NamaGroupMenu LIKE toLower('%?%')
    public Page<Menu> findByNamaContainsIgnoreCase(Pageable pageable, String nama);

    // UNTUK REPORT
    public List<Menu> findByNamaContainsIgnoreCase(String nama);

    @Query(value = "SELECT m FROM Menu m WHERE lower(m.groupMenu.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Menu> cariGroupMenu(Pageable pageable, String nama);

    @Query(value = "SELECT m FROM Menu m WHERE lower(m.groupMenu.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Menu> cariGroupMenu(String nama);

    public Optional<Menu> findTopByOrderByIdDesc();
}