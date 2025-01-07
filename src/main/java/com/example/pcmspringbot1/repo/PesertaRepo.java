package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.Peserta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Mon 19:31
@Last Modified Mon 19:31
Version 1.0
*/
public interface PesertaRepo extends JpaRepository<Peserta, Long> {

    // SELECT * FROM MstPeserta WHERE NamaPeserta = value1 OR Alamat = value2
    // derived Query
    public void findByNamaOrAlamat(String value1,String value2);

    // SELECT * FROM MstPeserta WHERE NamaPeserta LIKE toLower('%value1%')
    public void findByNamaContainsIgnoreCase(String value1);
//    contains
//    containing
//    isContaining

    @Query(value = "SELECT p From Peserta p WHERE p.nama=?1 OR p.alamat=?2")
    public void ambilDataNamaDanAlamat(String value1,String value2);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Peserta p SET p.alamat=?1 ,p.nama=?2 WHERE p.alamat LIKE toLower('%?3%')")
//    public void insertCustom(String value1,String value2 , String value3);

}
