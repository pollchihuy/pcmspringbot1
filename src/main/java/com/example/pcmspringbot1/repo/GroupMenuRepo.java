package com.example.pcmspringbot1.repo;

import com.example.pcmspringbot1.model.GroupMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GroupMenuRepo extends JpaRepository<GroupMenu, Integer> {


}
