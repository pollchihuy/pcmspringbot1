package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.dto.validasi.ValUserDTO;
import com.example.pcmspringbot1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Tue 20:20
@Last Modified Tue 20:20
Version 1.0
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    Map<String,String> mapFilter = new HashMap<>();


    public UserController() {
        filterColumnByMap();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> findAll(
            HttpServletRequest request){
        Pageable pageable = PageRequest.of(0,100, Sort.by("id"));//asc
        return userService.findAll(pageable,request);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValUserDTO userDTO, HttpServletRequest request){
        return userService.save(userService.convertToUser(userDTO),request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ValUserDTO userDTO, HttpServletRequest request){
        return userService.update(id, userService.convertToUser(userDTO),request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return userService.delete(id,request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,
                                           HttpServletRequest request){
        return userService.findById(id,request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> findByParam(
            @PathVariable(value = "sort") String sort,
            @PathVariable(value = "sortBy") String sortBy,//name
            @PathVariable(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request){
        Pageable pageable = null;
        sortBy = mapFilter.get(sortBy)==null?"id":mapFilter.get(sortBy);
        if(sort.equals("asc")){
            pageable = PageRequest.of(page,size, Sort.by(sortBy));//asc
        }else {
            pageable = PageRequest.of(page,size, Sort.by(sortBy).descending());//asc
        }
        return userService.findByParam(pageable,column,value,request);
    }

    @PostMapping("/files/upload/{username}")
    public ResponseEntity<Object> uploadImage(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        return userService.uploadImage(username,file,request);
    }

    public void filterColumnByMap(){
        mapFilter.put("nama","nama");
        mapFilter.put("username","username");
        mapFilter.put("password","password");
        mapFilter.put("email","email");
        mapFilter.put("umur","tanggalLahir");
        mapFilter.put("tanggalLahir","tanggalLahir");
        mapFilter.put("alamat","alamat");
        mapFilter.put("noHp","noHp");
    }
}