package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.core.IReportForm;
import com.example.pcmspringbot1.core.IService;
import com.example.pcmspringbot1.dto.response.RespMaster1DTO;
import com.example.pcmspringbot1.dto.validasi.ValMaster1DTO;
import com.example.pcmspringbot1.handler.ResponseHandler;
import com.example.pcmspringbot1.model.Master1;
import com.example.pcmspringbot1.repo.Master1Repo;
import com.example.pcmspringbot1.util.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Platform Code : AUT
 * Modul Code : 05
 * FV = Failed Validation
 * FE = Failed Error
 */
@Service
public class Master1Service implements IService<Master1>, IReportForm<Master1> {

    @Autowired
    private Master1Repo master1Repo;

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private TransformPagination transformPagination;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    private StringBuilder sbuild = new StringBuilder();


    @Override
    public ResponseEntity<Object> save(Master1 master1, HttpServletRequest request) {
        try{
            if(master1==null){
                return GlobalResponse.dataTidakValid("FVAUT05001",request);
            }
            master1Repo.save(master1);
        }catch (Exception e){
            LoggingFile.logException("Master1Service","save --> Line 42",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDisimpan("FEAUT05001",request);
        }

        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Master1 Master1, HttpServletRequest request) {
        try{
            if(Master1==null){
                return GlobalResponse.dataTidakValid("FVAUT05011",request);
            }
            Optional<Master1> Master1Optional = master1Repo.findById(id);
            if(!Master1Optional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            Master1 master1DB = Master1Optional.get();
            master1DB.setNama(Master1.getNama());

        }catch (Exception e){
            LoggingFile.logException("Master1Service","update --> Line 75",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiubah("FEAUT05011",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try{
            Optional<Master1> Master1Optional = master1Repo.findById(id);
            if(!Master1Optional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            master1Repo.deleteById(id);
        }catch (Exception e){
            LoggingFile.logException("Master1Service","delete --> Line 111",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiubah("FEAUT05021",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Master1> page = null;
        List<Master1> list = null;
        page = master1Repo.findAll(pageable);
        list = page.getContent();
        List<RespMaster1DTO> listDTO = convertToListRespMaster1DTO(list);

        if(list.isEmpty()){
            return GlobalResponse.dataTidakDitemukan(request);
        }

        Map<String, Object> mapList = transformPagination.transformPagination(listDTO,page,"id","");
        return GlobalResponse.dataResponseList(mapList,request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        RespMaster1DTO respMaster1DTO;
        try{
            Optional<Master1> Master1Optional = master1Repo.findById(id);
            if(!Master1Optional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            Master1 Master1DB = Master1Optional.get();
            respMaster1DTO = modelMapper.map(Master1DB, RespMaster1DTO.class);
        }catch (Exception e){
            LoggingFile.logException("Master1Service","findById --> Line 162",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiakses("FEAUT05041",request);
        }
        return new ResponseHandler().handleResponse("OK",
                HttpStatus.OK,
                respMaster1DTO,null,request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Master1> page = null;
        List<Master1> list = null;
        switch(columnName){

            case "nama": page = master1Repo.findByNamaContainsIgnoreCase(pageable,value);break;
            default : page = master1Repo.findAll(pageable);break;
        }
        list = page.getContent();
        if(list.isEmpty()){
            return GlobalResponse.dataTidakDitemukan(request);
        }
        List<RespMaster1DTO> listDTO = convertToListRespMaster1DTO(list);
        Map<String, Object> mapList = transformPagination.transformPagination(listDTO,page,columnName,value);
        return GlobalResponse.dataResponseList(mapList,request);
    }

    @Override
    public ResponseEntity<Object> uploadDataExcel(MultipartFile multipartFile, HttpServletRequest request) {

        String message = "";
        if(!ExcelReader.hasWorkBookFormat(multipartFile)){
            return GlobalResponse.formatHarusExcel(request);
        }

        try{
            List lt = new ExcelReader(multipartFile.getInputStream(),"sheet1").getDataMap();
            if(lt.isEmpty()){
                if(ExcelReader.hasWorkBookFormat(multipartFile)){
                    return GlobalResponse.dataFileKosong(request);
                }
            }
            master1Repo.saveAll(convertListWorkBookToListEntity(lt,1L));
        }catch (Exception e){
            LoggingFile.logException("Master1Service","upload excel --> Line 213",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.fileExcelGagalDiproses("FEAUT05061",request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public List<Master1> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long userId) {
        List<Master1> list = new ArrayList<>();
        for (int i = 0; i < workBookData.size(); i++) {
            Map<String, String> map = workBookData.get(i);
            Master1 master1 = new Master1();
            master1.setNama(map.get("nama-group-menu"));
            master1.setDeskripsi(String.valueOf(userId));
            list.add(master1);
        }
        return list;
    }

    @Override
    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<Master1> Master1List = null;
        switch (column){
            case "nama":Master1List= master1Repo.findByNamaContainsIgnoreCase(value);break;
            default:Master1List= master1Repo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<RespMaster1DTO> respMaster1DTOList = convertToListRespMaster1DTO(Master1List);
        if(respMaster1DTOList.isEmpty()){
            GlobalResponse.manualResponse(response,GlobalResponse.dataTidakDitemukan(request));
            return;
        }

        sbuild.setLength(0);
        String headerKey = "Content-Disposition";
        sbuild.setLength(0);

        String headerValue = sbuild.append("attachment; filename=group-menu_").
                append(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss.SSS").format(new Date())).append(".xlsx").toString();
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/octet-stream");

        Map<String,Object> map = GlobalFunction.convertClassToObject(new RespMaster1DTO());
        List<String> listTemp = new ArrayList<>();
        for(Map.Entry<String,Object> entry : map.entrySet()){
            listTemp.add(entry.getKey());
        }

        int intListTemp = listTemp.size();
        String [] headerArr = new String[intListTemp];// kolom judul di excel
        String [] loopDataArr = new String[intListTemp];//kolom judul untuk java reflection

        /** Untuk mempersiapkan data judul kolom nya */
        for(int i=0;i<intListTemp;i++){
            headerArr[i] = GlobalFunction.camelToStandar(String.valueOf(listTemp.get(i))).toUpperCase();
            loopDataArr[i] = listTemp.get(i);
        }
        /** Untuk mempersiapkan data body baris nya */
        int listRespMaster1DTOSize = respMaster1DTOList.size();
        String [][] strBody = new String[listRespMaster1DTOSize][intListTemp];
        for(int i=0;i<listRespMaster1DTOSize;i++){
            map = GlobalFunction.convertClassToObject(respMaster1DTOList.get(i));
            for(int j=0;j<intListTemp;j++){
                strBody[i][j] = String.valueOf(map.get(loopDataArr[j]));
            }
        }
        new ExcelWriter(strBody,headerArr,"sheet-1",response);
    }

    @Override
    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<Master1> Master1List = null;
        switch (column){
            case "nama":Master1List= master1Repo.findByNamaContainsIgnoreCase(value);break;
            default:Master1List= master1Repo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<RespMaster1DTO> respMaster1DTOList = convertToListRespMaster1DTO(Master1List);
        int intRespMaster1DTOList = respMaster1DTOList.size();

        if(respMaster1DTOList.isEmpty()){
            GlobalResponse.manualResponse(response,GlobalResponse.dataTidakDitemukan(request));
            return;
        }

        /** INI OBJECT MAP FINAL */
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new RespMaster1DTO());
        List<String> listTemp = new ArrayList<>();
        List<String> listHelper = new ArrayList<>();
        for (Map.Entry<String,Object> entry : mapColumnName.entrySet()) {
            listTemp.add(GlobalFunction.camelToStandar(entry.getKey()));
            listHelper.add(entry.getKey());
        }
        Map<String,Object> mapTemp = null;
        List<Map<String,Object>> listMap = new ArrayList<>();
        for(int i=0;i<respMaster1DTOList.size();i++){
            mapTemp = GlobalFunction.convertClassToObject(respMaster1DTOList.get(i));
            listMap.add(mapTemp);
        }

        map.put("title","REPORT DATA GROUP MENU");
        map.put("listKolom",listTemp);
        map.put("listHelper",listHelper);
        map.put("timestamp",new Date());
        map.put("totalData",intRespMaster1DTOList);
        map.put("listContent",listMap);
        map.put("username","Paul");
        context.setVariables(map);
        strHtml = springTemplateEngine.process("global-report",context);
        pdfGenerator.htmlToPdf(strHtml,"group-menu",response);
    }

    public void generateToPDFManual(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<Master1> Master1List = null;
        switch (column){
            case "nama":Master1List= master1Repo.findByNamaContainsIgnoreCase(value);break;
            default:Master1List= master1Repo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<RespMaster1DTO> respMaster1DTOList = convertToListRespMaster1DTO(Master1List);
        int intRespMaster1DTOList = respMaster1DTOList.size();

        if(respMaster1DTOList.isEmpty()){
            GlobalResponse.manualResponse(response,GlobalResponse.dataTidakDitemukan(request));
            return;
        }

        /** INI OBJECT MAP FINAL */
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new RespMaster1DTO());
        List<String> listTemp = new ArrayList<>();
        List<String> listHelper = new ArrayList<>();
        for (Map.Entry<String,Object> entry : mapColumnName.entrySet()) {
            listTemp.add(GlobalFunction.camelToStandar(entry.getKey()));
            listHelper.add(entry.getKey());
        }
        Map<String,Object> mapTemp = null;
        List<Map<String,Object>> listMap = new ArrayList<>();
        for(int i=0;i<listTemp.size();i++){
            mapTemp = GlobalFunction.convertClassToObject(Master1List.get(i));
            listMap.add(mapTemp);
        }
        map.put("title","REPORT GROUP MENU");
        map.put("timestamp",new Date());
        map.put("totalData",intRespMaster1DTOList);
        map.put("listContent",Master1List);
        map.put("username","Paul");
        context.setVariables(map);
        strHtml = springTemplateEngine.process("/report/Master1Report",context);
        pdfGenerator.htmlToPdf(strHtml,"group-menu",response);
    }

    public List<RespMaster1DTO> convertToListRespMaster1DTO(List<Master1> Master1List){
        return modelMapper.map(Master1List,new TypeToken<List<RespMaster1DTO>>(){}.getType());
    }

    public Master1 convertToListRespMaster1DTO(ValMaster1DTO Master1DTO){
        return modelMapper.map(Master1DTO,Master1.class);
    }


}