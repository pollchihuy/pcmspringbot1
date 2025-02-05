package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.dto.validasi.ValMenuDTO;
import com.example.pcmspringbot1.model.Menu;
import com.example.pcmspringbot1.service.MenuService;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    Map<String,String> mapFilter = new HashMap<>();


    public MenuController() {
        filterColumnByMap();
    }

    @GetMapping("")
    public ResponseEntity<Object> findAll(
            HttpServletRequest request){
        Pageable pageable = PageRequest.of(0,100, Sort.by("id"));//asc
        return menuService.findAll(pageable,request);
    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('Menu')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValMenuDTO menu, HttpServletRequest request){
        return menuService.save(menuService.convertToMenu(menu),request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Menu')")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ValMenuDTO menu, HttpServletRequest request){
        return menuService.update(id,menuService.convertToMenu(menu),request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Menu')")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return menuService.delete(id,request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Menu')")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,
                                           HttpServletRequest request){
        return menuService.findById(id,request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
    @PreAuthorize("hasAuthority('Menu')")
    public ResponseEntity<Object> findByParam(
            @PathVariable(value = "sort") String sort,
            @PathVariable(value = "sortBy") String sortBy,//name
            @PathVariable(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request){
        Pageable pageable = null;
        sortBy = mapFilter.get(sortBy)==null?"id":sortBy;
        if(sort.equals("asc")){
            pageable = PageRequest.of(page,size, Sort.by(sortBy));//asc
        }else {
            pageable = PageRequest.of(page,size, Sort.by(sortBy).descending());//asc
        }
        return menuService.findByParam(pageable,column,value,request);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('Menu')")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request){
        return menuService.uploadDataExcel(file,request);
    }

    @GetMapping("/excel")
    @PreAuthorize("hasAuthority('Menu')")
    public void download(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){
        menuService.downloadReportExcel(column,value,request,response);
    }

    @GetMapping("/pdf")
    @PreAuthorize("hasAuthority('Menu')")
    public void downloadReportPDFMenu(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){

        menuService.generateToPDF(column,value,request,response);
    }

    public void filterColumnByMap(){
        mapFilter.put("nama","nama");
        mapFilter.put("path","path");
        mapFilter.put("group","groupMenu");
    }
}
