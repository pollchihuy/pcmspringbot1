package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.core.IReportForm;
import com.example.pcmspringbot1.core.IService;
import com.example.pcmspringbot1.dto.response.RespGroupMenuDTO;
import com.example.pcmspringbot1.handler.ResponseHandler;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.repo.GroupMenuRepo;
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
 * Modul Code : 01
 * FV = Failed Validation
 * FE = Failed Error
 */
@Service
public class GroupMenuService implements IService<GroupMenu>, IReportForm<GroupMenu> {

    @Autowired
    private GroupMenuRepo groupMenuRepo;

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
    public ResponseEntity<Object> save(GroupMenu groupMenu, HttpServletRequest request) {
        try{
            if(groupMenu==null){
                return new ResponseHandler().handleResponse("DATA TIDAK VALID",
                        HttpStatus.BAD_REQUEST,
                        null,"FVAUT01001",request);
            }
            groupMenu.setCreatedBy("Paul");
            groupMenu.setCreatedDate(new Date());
            groupMenuRepo.save(groupMenu);

        }catch (Exception e){
            LoggingFile.logException("GroupMenuService","save --> Line 42",e, OtherConfig.getEnableLogFile());
            return new ResponseHandler().handleResponse("DATA GAGAL DISIMPAN",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,"FEAUT01001",request);
        }
        return new ResponseHandler().handleResponse("DATA BERHASIL DISIMPAN",
                HttpStatus.CREATED,
                null,null,request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, GroupMenu groupMenu, HttpServletRequest request) {
        try{
            if(groupMenu==null){
                return new ResponseHandler().handleResponse("DATA TIDAK VALID",
                        HttpStatus.BAD_REQUEST,
                        null,"FVAUT01011",request);
            }
            Optional<GroupMenu> groupMenuOptional = groupMenuRepo.findById(id);
            if(!groupMenuOptional.isPresent()){
                return new ResponseHandler().handleResponse("DATA TIDAK DITEMUKAN",
                        HttpStatus.BAD_REQUEST,
                        null,"FVAUT01012",request);
            }
            GroupMenu groupMenuDB = groupMenuOptional.get();
            groupMenuDB.setUpdatedBy("Reksa");
            groupMenuDB.setUpdatedDate(new Date());
            groupMenuDB.setNama(groupMenu.getNama());

        }catch (Exception e){
            LoggingFile.logException("GroupMenuService","update --> Line 75",e, OtherConfig.getEnableLogFile());
            return new ResponseHandler().handleResponse("DATA GAGAL DIUBAH",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,"FEAUT01011",request);
        }
        return new ResponseHandler().handleResponse("DATA BERHASIL DIUBAH",
                HttpStatus.OK,
                null,null,request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try{
            Optional<GroupMenu> groupMenuOptional = groupMenuRepo.findById(id);
            if(!groupMenuOptional.isPresent()){
                return new ResponseHandler().handleResponse("DATA TIDAK DITEMUKAN",
                        HttpStatus.BAD_REQUEST,
                        null,"FVAUT01021",request);
            }
            groupMenuRepo.deleteById(id);
        }catch (Exception e){
            LoggingFile.logException("GroupMenuService","delete --> Line 111",e, OtherConfig.getEnableLogFile());
            return new ResponseHandler().handleResponse("DATA GAGAL DIUBAH",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,"FEAUT01021",request);
        }
        return new ResponseHandler().handleResponse("DATA BERHASIL DIHAPUS",
                HttpStatus.OK,
                null,null,request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<GroupMenu> page = null;
        List<GroupMenu> list = null;
        page = groupMenuRepo.findAll(pageable);
        list = page.getContent();
        List<RespGroupMenuDTO> listDTO = convertToListRespGroupMenuDTO(list);

        if(list.isEmpty()){
            return new ResponseHandler().handleResponse("DATA TIDAK DITEMUKAN",
                    HttpStatus.BAD_REQUEST,
                    null,"FVAUT01031",request);
        }

        return new ResponseHandler().handleResponse("OK",
                HttpStatus.OK,
                transformPagination.transformPagination(listDTO,page,"id",""),null,request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        RespGroupMenuDTO respGroupMenuDTO;
        try{
            Optional<GroupMenu> groupMenuOptional = groupMenuRepo.findById(id);
            if(!groupMenuOptional.isPresent()){
                return new ResponseHandler().handleResponse("DATA TIDAK DITEMUKAN",
                        HttpStatus.BAD_REQUEST,
                        null,"FVAUT01041",request);
            }
            GroupMenu groupMenuDB = groupMenuOptional.get();
            respGroupMenuDTO = modelMapper.map(groupMenuDB, RespGroupMenuDTO.class);
        }catch (Exception e){
            LoggingFile.logException("GroupMenuService","findById --> Line 162",e, OtherConfig.getEnableLogFile());
            return new ResponseHandler().handleResponse("DATA GAGAL DIAKSES",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,"FEAUT01041",request);
        }
        return new ResponseHandler().handleResponse("OK",
                HttpStatus.OK,
                respGroupMenuDTO,null,request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<GroupMenu> page = null;
        List<GroupMenu> list = null;
        switch(columnName){

            case "nama": page = groupMenuRepo.findByNamaContainsIgnoreCase(pageable,value);break;
            default : page = groupMenuRepo.findAll(pageable);break;
        }
        list = page.getContent();
        if(list.isEmpty()){
            return new ResponseHandler().handleResponse("DATA TIDAK DITEMUKAN",
                    HttpStatus.BAD_REQUEST,
                    null,"FVAUT01051",request);
        }
        List<RespGroupMenuDTO> listDTO = convertToListRespGroupMenuDTO(list);

        return new ResponseHandler().handleResponse("OK",
                HttpStatus.OK,
                transformPagination.transformPagination(listDTO,page,columnName,value),null,request);
    }

    @Override
    public ResponseEntity<Object> uploadDataExcel(MultipartFile multipartFile, HttpServletRequest request) {

        String message = "";
        if(ExcelReader.hasWorkBookFormat(multipartFile)){
            return new ResponseHandler().handleResponse("Format Harus Excel",
                    HttpStatus.BAD_REQUEST,
                    null,"FVAUT01061",request);
        }

        try{
            List lt = new ExcelReader(multipartFile.getInputStream(),"sheet1").getDataMap();
            if(lt.isEmpty()){
                if(ExcelReader.hasWorkBookFormat(multipartFile)){
                    return new ResponseHandler().handleResponse("Tidak Ada Data Di File",
                            HttpStatus.BAD_REQUEST,
                            null,"FVAUT01062",request);
                }
            }
            groupMenuRepo.saveAll(convertListWorkBookToListEntity(lt,1L));
        }catch (Exception e){
            LoggingFile.logException("GroupMenuService","upload excel --> Line 213",e, OtherConfig.getEnableLogFile());
            return new ResponseHandler().handleResponse("UPLOAD EXCEL GAGAL",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,"FEAUT01061",request);
        }
        return new ResponseHandler().handleResponse("DATA BERHASIL DISIMPAN",
                HttpStatus.CREATED,
                null,null,request);
    }

    @Override
    public List<GroupMenu> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long userId) {
        List<GroupMenu> list = new ArrayList<>();
        for (int i = 0; i < workBookData.size(); i++) {
            Map<String, String> map = workBookData.get(i);
            GroupMenu groupMenu = new GroupMenu();
            groupMenu.setNama(map.get("nama-group-menu"));
            groupMenu.setCreatedBy(String.valueOf(userId));
            groupMenu.setCreatedDate(new Date());
            list.add(groupMenu);
        }
        return list;
    }

    @Override
    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<GroupMenu> groupMenuList = null;
        switch (column){
            case "nama":groupMenuList= groupMenuRepo.findByNamaContainsIgnoreCase(value);break;
            default:groupMenuList= groupMenuRepo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<RespGroupMenuDTO> respGroupMenuDTOList = convertToListRespGroupMenuDTO(groupMenuList);
        if(respGroupMenuDTOList.isEmpty()){
            return;
        }

        sbuild.setLength(0);
        String headerKey = "Content-Disposition";
        sbuild.setLength(0);

        String headerValue = sbuild.append("attachment; filename=group-menu_").
                append(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss.SSS").format(new Date())).append(".xlsx").toString();
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/octet-stream");

        Map<String,Object> map = GlobalFunction.convertClassToObject(new RespGroupMenuDTO());
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
        int listRespGroupMenuDTOSize = respGroupMenuDTOList.size();
        String [][] strBody = new String[listRespGroupMenuDTOSize][intListTemp];
        for(int i=0;i<listRespGroupMenuDTOSize;i++){
            map = GlobalFunction.convertClassToObject(respGroupMenuDTOList.get(i));
            for(int j=0;j<intListTemp;j++){
                strBody[i][j] = String.valueOf(map.get(loopDataArr[j]));
            }
        }
        new ExcelWriter(strBody,headerArr,"sheet-1",response);
    }

    @Override
    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<GroupMenu> groupMenuList = null;
        switch (column){
            case "nama":groupMenuList= groupMenuRepo.findByNamaContainsIgnoreCase(value);break;
            default:groupMenuList= groupMenuRepo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<RespGroupMenuDTO> respGroupMenuDTOList = convertToListRespGroupMenuDTO(groupMenuList);
        int intRespGroupMenuDTOList = respGroupMenuDTOList.size();

        if(respGroupMenuDTOList.isEmpty()){
            return;
        }

        /** INI OBJECT MAP FINAL */
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new RespGroupMenuDTO());
        List<String> listTemp = new ArrayList<>();
        List<String> listHelper = new ArrayList<>();
        for (Map.Entry<String,Object> entry : mapColumnName.entrySet()) {
            listTemp.add(GlobalFunction.camelToStandar(entry.getKey()));
            listHelper.add(entry.getKey());
        }
        Map<String,Object> mapTemp = null;
        List<Map<String,Object>> listMap = new ArrayList<>();
        for(int i=0;i<listTemp.size();i++){
            mapTemp = GlobalFunction.convertClassToObject(groupMenuList.get(i));
            listMap.add(mapTemp);
        }
        map.put("title","REPORT GROUP MENU");
        map.put("timestamp",new Date());
        map.put("totalData",intRespGroupMenuDTOList);
        map.put("listContent",groupMenuList);
        map.put("username","Paul");
        context.setVariables(map);
        strHtml = springTemplateEngine.process("/report/groupmenureport",context);
        pdfGenerator.htmlToPdf(strHtml,"group-menu",response);
    }

    public List<RespGroupMenuDTO> convertToListRespGroupMenuDTO(List<GroupMenu> groupMenuList){
        return modelMapper.map(groupMenuList,new TypeToken<List<RespGroupMenuDTO>>(){}.getType());
    }
}