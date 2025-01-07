//package com.example.pcmspringbot1.service;
//
//import com.example.pcmspringbot1.core.IReportForm;
//import com.example.pcmspringbot1.core.IService;
//import com.example.pcmspringbot1.model.Peserta;
//import com.example.pcmspringbot1.repo.PesertaRepo;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
///**
// * project code : PRD
// * modul code : 01
// * FE : Failed Error
// * FV : Failed Validation
// */
//@Service
//public class PesertaService implements IService<Peserta>, IReportForm {
//
//    @Autowired
//    private PesertaRepo pesertaRepo;
//
//    @Override
//    public void insert(Peserta peserta) {
//        if(peserta==null){
//            System.out.println("Error Code : FVPRD01001");
//        }
//        try{
//            pesertaRepo.save(peserta);
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("Error Code : FEPRD01001");
//        }
//    }
//
//    @Override
//    public void update(Long id, Peserta peserta) {
///**
// //         * Extract informasi dari Token
// //         */
//        Optional<Peserta> optionalPeserta = pesertaRepo.findById(id);
//        if(!optionalPeserta.isPresent()){
//            System.out.println("Data Tidak Ditemukan!!");
//            System.out.println("Error Code : FVPRD01011");
//        }
//        try{
//
//            Peserta pesertaNext = optionalPeserta.get();
//            pesertaNext.setAlamat(peserta.getAlamat());
//            pesertaNext.setNama(peserta.getNama());
//            pesertaNext.setUpdatedBy("Paul");
//            pesertaNext.setUpdatedDate(new Date());
//        }catch (Exception e){
//            System.out.println("Error Code : FEPRD01011");
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//
//    @Override
//    public Peserta findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<Peserta> findAll() {
//        return List.of();
//    }
//
//    @Override
//    public void upload() {
//
//    }
//
//    @Override
//    public void download() {
//
//    }
//
//    //    @Transactional
////    public void save(Peserta p){
////        /**
////         * Extract informasi dari Token
////         */
////        p.setCreatedDate(new Date());
////        p.setCreatedBy("Paul");
////        pesertaRepo.save(p);
////    }
////
////    // localhost:8080/peserta/{id}
////    @Transactional
////    public void update(Long id, Peserta peserta){
////        /**
////         * Extract informasi dari Token
////         */
////        Optional<Peserta> optionalPeserta = pesertaRepo.findById(id);
////        if(!optionalPeserta.isPresent()){
////            System.out.println("Data Tidak Ditemukan!!");
////        }
////        Peserta pesertaNext = optionalPeserta.get();
////        pesertaNext.setAlamat(peserta.getAlamat());
////        pesertaNext.setNama(peserta.getNama());
////        pesertaNext.setUpdatedBy("Paul");
////        pesertaNext.setUpdatedDate(new Date());
////    }
////
////    @Transactional
////    public void delete(Long id){
////        /**
////         * Extract informasi dari Token
////         */
////        try{
////            Optional<Peserta> optionalPeserta = pesertaRepo.findById(id);
////            if(!optionalPeserta.isPresent()){
////                System.out.println("Data Tidak Ditemukan!!");
////            }
////            pesertaRepo.deleteById(id);
////        }catch (Exception e){
////
////        }
////
////    }
////
////    public Peserta findById(Long id){
////        return pesertaRepo.findById(id).get();
////    }
////
////    public List<Peserta> findAll(){
////        return pesertaRepo.findAll();
////    }
//
//
//
//
//}
