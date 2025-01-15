package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.DataCoba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DataCobaRepo extends JpaRepository<DataCoba, Long> {

    public Page<DataCoba> findByUuidContainsIgnoreCase(Pageable pageable, String value);
    public List<DataCoba> findByUuidContainsIgnoreCase(String value);

    @Query(value = "SELECT a FROM DataCoba a WHERE CAST(a.duit as String) LIKE concat('%',?1,'%') ")
    public Page<DataCoba> cariDuit(Pageable pageable, String value);

    @Query(value = "SELECT a FROM DataCoba a WHERE CAST(a.duit as String) LIKE concat('%',?1,'%') ")
    public List<DataCoba> cariDuit(String value);

    public Page<DataCoba> findByNamaDepanContainsIgnoreCase(Pageable pageable, String value);
    public List<DataCoba> findByNamaDepanContainsIgnoreCase(String value);

}