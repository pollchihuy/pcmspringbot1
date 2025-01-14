package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.dto.validasi.ValGroupMenuDTO;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.service.GroupMenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/group-menu")
public class GroupMenuController {

    @Autowired
    private GroupMenuService groupMenuService;

    Map<String,String> mapFilter = new HashMap<>();


    public GroupMenuController() {
        filterColumnByMap();
    }

    @GetMapping("")
    public ResponseEntity<Object> findAll(
            HttpServletRequest request){
        Pageable pageable = PageRequest.of(0,10, Sort.by("id"));//asc
        return groupMenuService.findAll(pageable,request);
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@Valid @RequestBody ValGroupMenuDTO groupMenu, HttpServletRequest request){
        return groupMenuService.save(groupMenuService.convertToListRespGroupMenuDTO(groupMenu), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ValGroupMenuDTO groupMenu, HttpServletRequest request){
        return groupMenuService.update(id,groupMenuService.convertToListRespGroupMenuDTO(groupMenu),request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return groupMenuService.delete(id,request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,
                                           HttpServletRequest request){
        return groupMenuService.findById(id,request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
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
        return groupMenuService.findByParam(pageable,column,value,request);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request){
        return groupMenuService.uploadDataExcel(file,request);
    }

    @GetMapping("/excel")
    public void download(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){
        groupMenuService.downloadReportExcel(column,value,request,response);
    }

    @GetMapping("/pdf")
    public void downloadReportPDFGroupMenu(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){

        groupMenuService.generateToPDF(column,value,request,response);
    }

    public void filterColumnByMap(){
        mapFilter.put("nama","nama");
        mapFilter.put("group","group");
    }
}
