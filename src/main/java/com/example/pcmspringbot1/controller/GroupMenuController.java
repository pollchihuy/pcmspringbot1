package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.dto.validasi.ValGroupMenuDTO;
import com.example.pcmspringbot1.service.GroupMenuService;
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
@RequestMapping("/group-menu")
public class GroupMenuController {

    @Autowired
    private GroupMenuService groupMenuService;

    Map<String,String> mapFilter = new HashMap<>();


    public GroupMenuController() {
        filterColumnByMap();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('Group-Menu')")
    public ResponseEntity<Object> findAll(
            HttpServletRequest request){
        Pageable pageable = PageRequest.of(0,10, Sort.by("id"));//asc
        return groupMenuService.findAll(pageable,request);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('Group-Menu')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValGroupMenuDTO groupMenu, HttpServletRequest request){
        return groupMenuService.save(groupMenuService.convertToListRespGroupMenuDTO(groupMenu), request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Group-Menu')")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ValGroupMenuDTO groupMenu, HttpServletRequest request){
        return groupMenuService.update(id,groupMenuService.convertToListRespGroupMenuDTO(groupMenu),request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Group-Menu')")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return groupMenuService.delete(id,request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Group-Menu')")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,
                                           HttpServletRequest request){
        return groupMenuService.findById(id,request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
    @PreAuthorize("hasAuthority('Group-Menu')")
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
    @PreAuthorize("hasAuthority('Group-Menu')")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request){
        return groupMenuService.uploadDataExcel(file,request);
    }

    @GetMapping("/excel")
    @PreAuthorize("hasAuthority('Group-Menu')")
    public void download(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){
        groupMenuService.downloadReportExcel(column,value,request,response);
    }

    @GetMapping("/pdf")
    @PreAuthorize("hasAuthority('Group-Menu')")
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
