package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.dto.validasi.ValMaster1DTO;
import com.example.pcmspringbot1.service.Master1Service;
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

@RestController
@RequestMapping("/master1")
public class Master1Controller {

    @Autowired
    private Master1Service master1Service;
    Map<String,String> mapFilter = new HashMap<>();

    public Master1Controller() {
        filterColumnByMap();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('Master1')")
    public ResponseEntity<Object> findAll(
            HttpServletRequest request){
        Pageable pageable = PageRequest.of(0,10, Sort.by("id"));//asc
        return master1Service.findAll(pageable,request);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('Master1')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValMaster1DTO valMaster1DTO, HttpServletRequest request){
        return master1Service.save(master1Service.convertToListRespMaster1DTO(valMaster1DTO), request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Master1')")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ValMaster1DTO valMaster1DTO, HttpServletRequest request){
        return master1Service.update(id, master1Service.convertToListRespMaster1DTO(valMaster1DTO),request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Master1')")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return master1Service.delete(id,request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Master1')")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,
                                           HttpServletRequest request){
        return master1Service.findById(id,request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
    @PreAuthorize("hasAuthority('Master1')")
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
        return master1Service.findByParam(pageable,column,value,request);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('Master1')")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request){
        return master1Service.uploadDataExcel(file,request);
    }

    @GetMapping("/excel")
    @PreAuthorize("hasAuthority('Master1')")
    public void download(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){
        master1Service.downloadReportExcel(column,value,request,response);
    }

    @GetMapping("/pdf")
    @PreAuthorize("hasAuthority('Master1')")
    public void downloadReportPDFGroupMenu(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){

        master1Service.generateToPDF(column,value,request,response);
    }

    public void filterColumnByMap(){
        mapFilter.put("nama","namaGroupMenu");
        mapFilter.put("group","group");
    }
}
