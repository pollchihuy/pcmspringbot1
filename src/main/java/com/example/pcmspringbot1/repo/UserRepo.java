package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo  extends JpaRepository<User,Long> {

    public Page<User> findByAlamatContainsIgnoreCase(org.springframework.data.domain.Pageable pageable, String nama);
    public Page<User> findByUsernameContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByEmailContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByNoHpContainsIgnoreCase(Pageable pageable, String nama);
    public Page<User> findByNamaContainsIgnoreCase(Pageable pageable, String nama);

    public Optional<User> findByUsername(String value);
    public Optional<User> findByEmail(String value);
    public List<User> findByUsernameOrNoHpOrEmailAndIsRegistered(String value1,String value2, String value3,Boolean value4);
}
