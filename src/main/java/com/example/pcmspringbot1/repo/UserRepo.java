package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo  extends JpaRepository<User,Long> {

    public Page<User> findByAlamatContainsIgnoreCase(org.springframework.data.domain.Pageable pageable, String nama);
    public Page<User> findByUsernameContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByPasswordContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByEmailContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByNoHpContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByNamaContainsIgnoreCase(Pageable pageable, String nama);

    public Optional<User> findByUsername(String value);
    public Optional<User> findByEmail(String value);
    public List<User> findByUsernameOrNoHpOrEmailAndIsRegistered(String value1,String value2, String value3,Boolean value4);

    /** update when database migration */
    @Query(value = "SELECT  x FROM User x WHERE CAST(DATEDIFF(year ,x.tanggalLahir,CURRENT_TIMESTAMP)AS STRING) LIKE CONCAT('%',?1,'%') ")
    public Page<User> cariUmur(Pageable pageable, String value);
}
