package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.Akses;
import com.example.pcmspringbot1.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AksesRepo extends JpaRepository<Akses,Long> {
//public interface AksesRepo extends CrudRepository<Akses,Long> {

    //DERIVED query
    //select * from MstAkses WHERE NamaGroupMenu LIKE toLower('%?%')
    public Page<Akses> findByNamaContainsIgnoreCase(Pageable pageable, String nama);

    // UNTUK REPORT
    public List<Akses> findByNamaContainsIgnoreCase(String nama);
}
