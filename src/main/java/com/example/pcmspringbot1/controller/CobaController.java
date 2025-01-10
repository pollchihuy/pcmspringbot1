package com.example.pcmspringbot1.controller;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 20:31
@Last Modified Fri 20:31
Version 1.0
*/

import com.example.pcmspringbot1.config.CobaConfig;
import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.dto.response.UserRespDTO;
import com.example.pcmspringbot1.mapper.UserMapper;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.model.User;
import com.example.pcmspringbot1.service.GroupMenuService;
import com.example.pcmspringbot1.util.GlobalFunction;
import com.example.pcmspringbot1.util.LoggingFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("auth")
public class CobaController {

    @Autowired
    private GroupMenuService groupMenuService;

    @Value("${cumi.goreng}")
    String strData ;

    @Autowired
    GlobalFunction globalFunction;

    Random rand = new Random();

    @Autowired
    UserMapper userMapper;
    ModelMapper modelMapper = new ModelMapper();

    Map<String,String> mapFilter = new HashMap<>();

    public CobaController() {
        filterColumnByMap();
    }

    @GetMapping("hello1")
    public String htmlRender(){

//        System.out.println("Cumi.Goreng Value : "+strData);
//        globalFunction.ok = "INI DATA";
        GlobalFunction.print("Hahaha");
        return CobaConfig.getStrCoba();
    }

    @PostMapping("/req-dto")
    public UserRespDTO callUserDTO(@RequestBody User user){
        return userMapper.userToUserRespDTO(user);
    }

    @PostMapping("/req-dto-auto")
    public UserRespDTO callUserDTOAuto(@RequestBody User user){
        return modelMapper.map(user, UserRespDTO.class);
    }

    @PostMapping("/req-dto-auto-list")
    public List<UserRespDTO> callUserDTOAutoList(@RequestBody List<User> user){
        return modelMapper.map(user, new TypeToken<List<UserRespDTO>>() {}.getType());
    }

    @GetMapping("/test-log")
    public String cobaLog(){
        String exceptionz = "";
        try{
            int intX = 1/0;
        }catch (Exception e){
            exceptionz = "Errornya adalah "+e.getMessage();
            LoggingFile.logException("CobaController","cobaLog",e, OtherConfig.getEnableLogFile());
        }

        return exceptionz;
    }

    @PostMapping("/save-group-menu")
    public ResponseEntity<Object> save(@RequestBody GroupMenu groupMenu, HttpServletRequest request){
        return groupMenuService.save(groupMenu,request);
    }

    @PutMapping("/update-group-menu/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
             @RequestBody GroupMenu groupMenu, HttpServletRequest request){
        return groupMenuService.update(id,groupMenu,request);
    }

    @DeleteMapping("/delete-group-menu/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return groupMenuService.delete(id,request);
    }

    @GetMapping("/find-all-group-menu")
    public ResponseEntity<Object> findAll(
            HttpServletRequest request){
        Pageable pageable = PageRequest.of(0,3, Sort.by("id"));//asc
//        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());//desc
        return groupMenuService.findAll(pageable,request);
    }

    @GetMapping("/find-by-id-group-menu/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,
            HttpServletRequest request){
        return groupMenuService.findById(id,request);
    }

    /**
     * Sorting column - PV
     * Sorting ASC / DESC - PV
     * Page - PV
     * Size Per Page - RP
     * Column - RP
     * Value - RP
     */
    @GetMapping("/find-by-param-group-menu/{sort}/{sortBy}/{page}")
    public ResponseEntity<Object> findByParam(
                @PathVariable(value = "sort") String sort,
                @PathVariable(value = "sortBy") String sortBy,//name
                @PathVariable(value = "page") Integer page,
               @RequestParam(value = "size") Integer size,
               @RequestParam(value = "column") String column,
               @RequestParam(value = "value") String value,
               HttpServletRequest request){
        Pageable pageable = null;
//        /** contoh 1 - pakai switch case */
//        sortBy = filterColumn(sortBy);
//        /** contoh 2 - pakai map */
//        sortBy = mapFilter.get(sortBy)==null?"id":sortBy;
        sortBy = mapFilter.get(sortBy)==null?"id":sortBy;
        if(sort.equals("asc")){
            pageable = PageRequest.of(page,size, Sort.by(sortBy));//asc
        }else {
            pageable = PageRequest.of(page,size, Sort.by(sortBy).descending());//asc
        }
        return groupMenuService.findByParam(pageable,column,value,request);
    }

    @PostMapping("/upload-excel-group-menu")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file")MultipartFile file,
            HttpServletRequest request){
        return groupMenuService.uploadDataExcel(file,request);
    }

    @GetMapping("/download-excel-group-menu")
    public void download(
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request,
            HttpServletResponse response){
        groupMenuService.downloadReportExcel(column,value,request,response);
    }

    public String filterColumn(String column){
        switch (column){
            case "name": return column;
            default:return "id";
        }
    }

    public void filterColumnByMap(){
        mapFilter.put("nama","nama");
    }
}