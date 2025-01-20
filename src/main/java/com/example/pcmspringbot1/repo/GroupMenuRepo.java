package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.GroupMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMenuRepo extends JpaRepository<GroupMenu, Long> {

    //DERIVED query
    //select * from MstGroupMenu WHERE NamaGroupMenu LIKE toLower('%?%')
    public Page<GroupMenu> findByNamaContainsIgnoreCase(Pageable pageable,String nama);

    // UNTUK REPORT
    public List<GroupMenu> findByNamaContainsIgnoreCase(String nama);
    public Optional<GroupMenu> findTopByOrderByIdDesc();

}