package com.example.pcmspringbot1.controller;


import com.example.pcmspringbot1.dto.ContohDTO;
import com.example.pcmspringbot1.dto.LoginDTO;
import com.example.pcmspringbot1.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("coba")
public class PostController {

    @PostMapping("/first")
    public String firstPost(@Valid @RequestBody LoginDTO loginDTO){
        System.out.println("Password : "+loginDTO.getPassword());
        System.out.println("Username : "+loginDTO.getUsername());
        System.out.println("Nama Depan : "+loginDTO.getNamaDepan());
        return "First Post";
    }

    /**
    {
        "id" : 193553,
            "nama" : "Tessy Wahyuni",
            "jenis-kelamin" : "Pria",
            "umur" : 78,
            "alamat" : {
        "jalan": "Jalan Rusak",
                "nomor" : 145,
                "kota" : "Jakarta Tenggara",
                "kode-pos" : 88134
    },
        "list":[
        "Nilai 1",
                "Nilai 2",
                "Nilai 3"
	],
        "contoh":[
        {
            "data1":"data ke 1 dari data pertama",
                "data2":"data ke 2 dari data pertama"
        },
        {
            "data1":"data ke 1 dari data kedua",
                "data2":"data ke 2 dari data kedua"
        },
        {
            "data1":"data ke 1 dari data ketiga",
                "data2":"data ke 2 dari data ketiga"
        }

	]
    }
    */
    @PostMapping("/second")
    public String secondPost(@Valid @RequestBody UserDTO userDTO){
        System.out.println("ID "+userDTO.getId());
        System.out.println("Nama "+userDTO.getNama());
        System.out.println("Jenis Kelamin "+userDTO.getJenisKelamin());
        System.out.println("umur "+userDTO.getUmur());
        System.out.println("Jalan "+userDTO.getAlamatDTO().getJalan());
        System.out.println("Nomor "+userDTO.getAlamatDTO().getNomor());
        System.out.println("Kota "+userDTO.getAlamatDTO().getKota());
        System.out.println("KodePos "+userDTO.getAlamatDTO().getKodePos());
        List<String> lt = userDTO.getList();
        int length = lt.size();
        for (int i = 0; i < length; i++) {
            System.out.println(lt.get(i));
        }

        List<ContohDTO> ltContoh = userDTO.getLtContohDTO();
        int lengthContoh = ltContoh.size();
        for (int i = 0; i < lengthContoh; i++) {
            System.out.println(ltContoh.get(i).getData1());
            System.out.println(ltContoh.get(i).getData2());
            System.out.println("=====================================");
        }
        return "Second Post";
    }

    @PostMapping("/upload")
    public String multipartRequest(
            @RequestParam(value = "dokumen") MultipartFile[] dok,
            @RequestParam(value = "text-arr") String [] textArr
    ){
        for (MultipartFile file : dok) {
            System.out.println(file.getOriginalFilename());
        }

        for (String text : textArr) {
            System.out.println(text);
        }

        return "Multipart Request";
    }
}