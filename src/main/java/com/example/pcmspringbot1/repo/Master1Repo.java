package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.Master1;
import com.example.pcmspringbot1.model.Master1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Master1Repo extends JpaRepository<Master1, Long> {

    //DERIVED query
    //select * from MstMaster1 WHERE NamaMaster1 LIKE toLower('%?%')
    public Page<Master1> findByNamaContainsIgnoreCase(Pageable pageable, String nama);
    public Page<Master1> findByDeskripsiContainsIgnoreCase(Pageable pageable, String nama);

    @Query(value = "SELECT  x FROM Master1 x WHERE CAST(x.jumlah AS STRING ) LIKE CONCAT('%',?1,'%') ")
    public Page<Master1> findByJumlahContainsIgnoreCase(Pageable pageable, String nama);

    @Query(value = "SELECT  x FROM Master1 x WHERE CAST(x.total AS STRING ) LIKE CONCAT('%',?1,'%') ")
    public Page<Master1> findByTotalContainsIgnoreCase(Pageable pageable, String nama);

    // UNTUK REPORT
    public List<Master1> findByNamaContainsIgnoreCase(String nama);
    public List<Master1> findByDeskripsiContainsIgnoreCase(String nama);

    @Query(value = "SELECT  x FROM Master1 x WHERE CAST(x.jumlah AS STRING ) LIKE CONCAT('%',?1,'%') ")
    public List<Master1> findByJumlahContainsIgnoreCase(String nama);


    @Query(value = "SELECT  x FROM Master1 x WHERE CAST(x.total AS STRING ) LIKE CONCAT('%',?1,'%') ")
    public List<Master1> findByTotalContainsIgnoreCase(String nama);
    public Optional<Master1> findTopByOrderByIdDesc();

}