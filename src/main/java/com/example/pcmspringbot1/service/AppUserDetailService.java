package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.core.SMTPCore;
import com.example.pcmspringbot1.dto.response.MenuLoginDTO;
import com.example.pcmspringbot1.dto.response.RespMenuDTO;
import com.example.pcmspringbot1.dto.validasi.ValRegisDTO;
import com.example.pcmspringbot1.dto.validasi.ValLoginDTO;
import com.example.pcmspringbot1.dto.validasi.ValVerifyRegisDTO;
import com.example.pcmspringbot1.handler.ResponseHandler;
import com.example.pcmspringbot1.model.Akses;
import com.example.pcmspringbot1.model.User;
import com.example.pcmspringbot1.repo.UserRepo;
import com.example.pcmspringbot1.security.BcryptImpl;
import com.example.pcmspringbot1.security.Crypto;
import com.example.pcmspringbot1.security.JwtUtility;
import com.example.pcmspringbot1.util.SendMailOTP;
import com.example.pcmspringbot1.util.TransformationData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.patterns.IToken;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Thu 19:45
@Last Modified Thu 19:45
Version 1.0
*/
@Service
@Transactional
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper ;
//    private ModelMapper modelMapper = new ModelMapper();
    private Random random = new Random();

    @Autowired
    private JwtUtility jwtUtility;

    public ResponseEntity<Object> login(User user, HttpServletRequest request) throws UsernameNotFoundException {
        Optional<User> optUser = userRepo.findByUsername(user.getUsername());
        if (!optUser.isPresent()) {
            return new ResponseHandler().handleResponse("USER TIDAK TERDAFTAR",
                    HttpStatus.BAD_REQUEST,
                    null,"X01004",request);
        }
        User userDB = optUser.get();
        if(!userDB.getRegistered()){
            return new ResponseHandler().handleResponse("USER TIDAK TERDAFTAR",
                    HttpStatus.BAD_REQUEST,
                    null,"X01005",request);
        }
        String password = userDB.getPassword();// yang sudah di hash dari table
        if(!BcryptImpl.verifyHash((user.getUsername()+user.getPassword()), password)){
            return new ResponseHandler().handleResponse("USER NAME ATAU PASSWORD SALAH",
                    HttpStatus.BAD_REQUEST,
                    null,"X01006",request);
        }

        UserDetails userDetails = loadUserByUsername(user.getUsername());
        Map<String,Object> mapForJwt = new HashMap<>();
//        mapForJwt.put("un", userDB.getUsername());
//        mapForJwt.put("pwd", userDB.getPassword());
        mapForJwt.put("uid",userDB.getId());
        mapForJwt.put("ml",userDB.getEmail());
        mapForJwt.put("nl",userDB.getNama());
        mapForJwt.put("pn",userDB.getNoHp());

        String token = jwtUtility.generateToken(userDetails,mapForJwt);
        Map<String,Object> mapResponse = new HashMap<>();
        mapResponse.put("token", Crypto.performEncrypt(token));//kalau mau di encrypt
        List<MenuLoginDTO> ltMenu =  modelMapper.map(userDB.getAkses().getLtMenu(),new TypeToken<List<MenuLoginDTO>>(){}.getType());
//        mapResponse.put("token",token);//kalau mau di encrypt
        mapResponse.put("menu",new TransformationData().doTransformAksesMenuLogin(ltMenu));
        mapResponse.put("urlImage",userDB.getLinkImage());
        return ResponseEntity.status(HttpStatus.OK).body(mapResponse);
    }

    @Transactional
    public ResponseEntity<Object> regis(User user, HttpServletRequest request) throws UsernameNotFoundException {

        /** default untuk proses registrasi, user akan mendapatkan akses dengan ID 2 */
        Akses akses = new Akses();
        akses.setId(2L);

        Optional<User> optUser = userRepo.findByUsername(user.getUsername());
        User userDB = null;
        Integer otp = 0;

        if (optUser.isPresent()) {
            userDB = optUser.get();
            if(userDB.getRegistered()){
                return new ResponseHandler().handleResponse("USER SUDAH TERDAFTAR",
                        HttpStatus.BAD_REQUEST,
                        null,"X01007",request);
            }else {
                /** pengecekan seluruh data selain username , jika email atau pun no hp yang ada dan pernah teregistrasi ataupun tidak maka
                 *  user diminta untuk mengganti data tersebut
                 */
                List<User> ltUser = userRepo.findByUsernameOrNoHpOrEmailAndIsRegistered(user.getUsername(),user.getNoHp(),user.getEmail(),true);
                User userCheck = ltUser.get(0);
                if(!ltUser.isEmpty()){
                    /** email sudah ada di database */
                    if(userCheck.getEmail().equals(user.getEmail())){
                        return new ResponseHandler().handleResponse("EMAIL SUDAH TERPAKAI",
                                HttpStatus.BAD_REQUEST,
                                null,"X01008",request);
                    }
                    /** no hp sudah ada di database */
                    if(userCheck.getNoHp().equals(user.getNoHp())){
                        return new ResponseHandler().handleResponse("NO-HP SUDAH TERPAKAI",
                                HttpStatus.BAD_REQUEST,
                                null,"X01008",request);
                    }
                }else{
                    /** PERNAH REGISTRASI TAPI BELUM SELESAI */
                    userDB.setAlamat(user.getAlamat());
                    userDB.setNoHp(user.getNoHp());
                    userDB.setEmail(user.getEmail());
                    userDB.setNama(user.getNama());
                    userDB.setNoHp(user.getNoHp());
                    userDB.setPassword(BcryptImpl.hash(user.getUsername()+user.getPassword()));
                    userDB.setTanggalLahir(user.getTanggalLahir());
                    userDB.setUpdatedBy(userDB.getNama());
                    userDB.setUpdatedDate(new Date());
                    otp = random.nextInt(111111,999999);
                    userDB.setOtp(BcryptImpl.hash(String.valueOf(otp)));
                    userDB.setAkses(akses);
                }
            }
        }else {
            user.setCreatedBy("Paul");
            user.setCreatedDate(new Date());
            user.setPassword(BcryptImpl.hash(user.getUsername()+user.getPassword()));
            otp = random.nextInt(111111,999999);
            user.setOtp(BcryptImpl.hash(String.valueOf(otp)));
            user.setAkses(akses);
            userRepo.save(user);
        }
        /** kirim verifikasi email */
        Map<String,Object> mapResponse = new HashMap<>();
        if(OtherConfig.getEnableAutomation().equals("y")){
            mapResponse.put("token", otp);
        }
        /** kalau mau send email, lihat di class ContohController API kirim-email  */
        SendMailOTP.verifyRegisOTP("OTP Registrasi",user.getNama(),user.getEmail(),String.valueOf(otp));
//        mapResponse.put("estafet",);//untuk security estafet work flow pada form
        return ResponseEntity.status(HttpStatus.OK).body(mapResponse);
    }

    @Transactional
    public ResponseEntity<Object> verifyRegis(User user, HttpServletRequest request) throws UsernameNotFoundException {
        if(user==null){
            return new ResponseHandler().handleResponse("DATA TIDAK VALID",
                    HttpStatus.BAD_REQUEST,
                    null,"X01009",request);
        }
        Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
        if (!optionalUser.isPresent()) {
            return new ResponseHandler().handleResponse("DATA TIDAK VALID",
                    HttpStatus.BAD_REQUEST,
                    null,"X01008",request);
        }
        User userDB = optionalUser.get();
        if(!BcryptImpl.verifyHash(user.getOtp(),userDB.getOtp())){
            return new ResponseHandler().handleResponse("TOKEN SALAH",
                    HttpStatus.BAD_REQUEST,
                    null,"X01009",request);
        }

        userDB.setOtp(BcryptImpl.hash(String.valueOf(random.nextInt(111111,999999))));
        userDB.setRegistered(true);
        return new ResponseHandler().handleResponse("Registrasi Berhasil !!",
                HttpStatus.OK,
                null,null,request);
//        return ResponseEntity.status(HttpStatus.OK).body("Registrasi Berhasil !!");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>  opUser = userRepo.findByUsername(username);
        if(!opUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = opUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),user.getAuthorities());
    }

    public User convertToUser(ValLoginDTO valLoginDTO) {
        return modelMapper.map(valLoginDTO,User.class);
    }

    public User convertToUser(ValRegisDTO valRegisDTO) {
        return modelMapper.map(valRegisDTO,User.class);
    }

    public User convertToUser(ValVerifyRegisDTO valVerifyRegisDTO) {
        return modelMapper.map(valVerifyRegisDTO,User.class);
    }
}
