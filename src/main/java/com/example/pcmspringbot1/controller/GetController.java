//package com.example.pcmspringbot1.controller;
//
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("coba")
//public class GetController {
//
//    @GetMapping("/")
//    public String firstPage(){
//        return "Welcome+GET";
//    }
//
//
//    /** Request Method + End Point + Path Variable + Query Param */
//    @GetMapping()
//    public String secondPage(){
//        return "Second Page";
//    }
//
//    @GetMapping("/2/{nama}/{id}")//path variable
//    public String secondPage(@PathVariable(value = "nama") String strNama,
//    @PathVariable(value = "id") Long id
//    ){
//        System.out.println("Nama : "+strNama);
//        System.out.println("Long : "+id);
//        return "Second Page";
//    }
//
//    @GetMapping("/3/{namaz}/{idz}")//path variable
//    public String secondPage(@PathVariable(value = "namaz") String strNama,
//                             @PathVariable(value = "idz") String id
//    ){
//        System.out.println("Nama : "+strNama);
//        System.out.println("Long : "+id);
//        return "Second Page";
//    }
//
//    @GetMapping("/4")//Query Param / Request Param
//    public String thirdPage(
//            @RequestParam(value = "nama") String strNama
//    ){
//
//        System.out.println("Nama : "+strNama);
//        return "Second Page";
//    }
//}