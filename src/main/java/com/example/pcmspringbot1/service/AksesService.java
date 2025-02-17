package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.core.IReportForm;
import com.example.pcmspringbot1.core.IService;
import com.example.pcmspringbot1.dto.report.ReportAksesDTO;
import com.example.pcmspringbot1.dto.response.RespAksesDTO;
import com.example.pcmspringbot1.dto.response.SelectAksesDTO;
import com.example.pcmspringbot1.dto.response.TableAksesDTO;
import com.example.pcmspringbot1.dto.response.TableMenuDTO;
import com.example.pcmspringbot1.dto.validasi.ValAksesDTO;
import com.example.pcmspringbot1.handler.ResponseHandler;
import com.example.pcmspringbot1.model.Akses;
import com.example.pcmspringbot1.repo.AksesRepo;
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
 * Modul Code : 03
 * FV = Failed Validation
 * FE = Failed Error
 */
@Service
public class AksesService implements IService<Akses>, IReportForm<Akses> {

    @Autowired
    private AksesRepo aksesRepo;

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
    public ResponseEntity<Object> save(Akses akses, HttpServletRequest request) {
        Map<String,Object> token = GlobalFunction.extractToken(request);
        try{
            if(akses==null){
                return GlobalResponse.dataTidakValid("FVAUT03001",request);
            }
            akses.setCreatedBy(token.get("userId").toString());
            akses.setCreatedDate(new Date());
            akses.setLtMenu(akses.getLtMenu());

            aksesRepo.save(akses);
        }catch (Exception e){
            LoggingFile.logException("AksesService","save --> Line 42",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDisimpan("FEAUT03001",request);
        }

        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Akses akses, HttpServletRequest request) {
        Map<String,Object> token = GlobalFunction.extractToken(request);
        try{
            if(akses==null){
                return GlobalResponse.dataTidakValid("FVAUT03011",request);
            }
            Optional<Akses> aksesOptional = aksesRepo.findById(id);
            if(!aksesOptional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            Akses aksesDB = aksesOptional.get();
            aksesDB.setUpdatedBy(token.get("userId").toString());
            aksesDB.setUpdatedDate(new Date());
            aksesDB.setNama(akses.getNama());
            aksesDB.setLtMenu(akses.getLtMenu());

        }catch (Exception e){
            LoggingFile.logException("AksesService","update --> Line 75",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiubah("FEAUT03011",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        Map<String,Object> token = GlobalFunction.extractToken(request);
        try{
            Optional<Akses> aksesOptional = aksesRepo.findById(id);
            if(!aksesOptional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            aksesRepo.deleteById(id);
        }catch (Exception e){
            LoggingFile.logException("AksesService","delete --> Line 111",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiubah("FEAUT03021",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Akses> page = null;
        List<Akses> list = null;
        page = aksesRepo.findAll(pageable);
        list = page.getContent();
//        List<RespAksesDTO> listDTO = convertToListRespAksesDTO(list);
        List<TableAksesDTO> listDTO = convertToTableAksesDTO(list);

        if(list.isEmpty()){
            return GlobalResponse.dataTidakDitemukan(request);
        }

        Map<String, Object> mapList = transformPagination.transformPagination(listDTO,page,"id","");
        return GlobalResponse.dataResponseList(mapList,request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
//        RespAksesDTO respAksesDTO;
        SelectAksesDTO selectAksesDTO;
        try{
            Optional<Akses> aksesOptional = aksesRepo.findById(id);
            if(!aksesOptional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            Akses aksesDB = aksesOptional.get();
//            respAksesDTO = modelMapper.map(aksesDB, RespAksesDTO.class);
            selectAksesDTO = modelMapper.map(aksesDB, SelectAksesDTO.class);
        }catch (Exception e){
            LoggingFile.logException("AksesService","findById --> Line 162",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiakses("FEAUT03041",request);
        }
        return new ResponseHandler().handleResponse("OK",
                HttpStatus.OK,
                selectAksesDTO,null,request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Akses> page = null;
        List<Akses> list = null;
        switch(columnName){

            case "nama": page = aksesRepo.findByNamaContainsIgnoreCase(pageable,value);break;
            default : page = aksesRepo.findAll(pageable);break;
        }
        list = page.getContent();
        if(list.isEmpty()){
            return GlobalResponse.dataTidakDitemukan(request);
        }
//        List<RespAksesDTO> listDTO = convertToListRespAksesDTO(list);
        List<TableAksesDTO> listDTO = convertToTableAksesDTO(list);
        Map<String, Object> mapList = transformPagination.transformPagination(listDTO,page,columnName,value);
        return GlobalResponse.dataResponseList(mapList,request);
    }

    @Override
    public ResponseEntity<Object> uploadDataExcel(MultipartFile multipartFile, HttpServletRequest request) {
        Map<String,Object> token = GlobalFunction.extractToken(request);
        String message = "";
        if(ExcelReader.hasWorkBookFormat(multipartFile)){
            return GlobalResponse.formatHarusExcel(request);
        }

        try{
            List lt = new ExcelReader(multipartFile.getInputStream(),"sheet1").getDataMap();
            if(lt.isEmpty()){
                if(ExcelReader.hasWorkBookFormat(multipartFile)){
                    return GlobalResponse.dataFileKosong(request);
                }
            }
            aksesRepo.saveAll(convertListWorkBookToListEntity(lt,Long.parseLong(token.get("userId").toString())));
        }catch (Exception e){
            LoggingFile.logException("AksesService","upload excel --> Line 213",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.fileExcelGagalDiproses("FEAUT03061",request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public List<Akses> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long userId) {
        List<Akses> list = new ArrayList<>();
        for (int i = 0; i < workBookData.size(); i++) {
            Map<String, String> map = workBookData.get(i);
            Akses menu = new Akses();
            menu.setNama(map.get("nama-menu"));
            menu.setCreatedBy(String.valueOf(userId));
            menu.setCreatedDate(new Date());
            list.add(menu);
        }
        return list;
    }

    @Override
    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<Akses> aksesList = null;
        switch (column){
            case "nama":aksesList= aksesRepo.findByNamaContainsIgnoreCase(value);break;
//            case "group":menuList= menuRepo.cariGroupAkses(value);break;
            default:aksesList= aksesRepo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<ReportAksesDTO> reportAksesDTOList = convertToListReportAksesDTO(aksesList);
        if(reportAksesDTOList.isEmpty()){
            GlobalResponse.manualResponse(response,GlobalResponse.dataTidakDitemukan(request));
            return;
        }

        sbuild.setLength(0);
        String headerKey = "Content-Disposition";
        sbuild.setLength(0);

        String headerValue = sbuild.append("attachment; filename=akses_").
                append(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss.SSS").format(new Date())).append(".xlsx").toString();
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/octet-stream");

        Map<String,Object> map = GlobalFunction.convertClassToObject(new ReportAksesDTO());
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
        int listRespGroupAksesDTOSize = reportAksesDTOList.size();
        String [][] strBody = new String[listRespGroupAksesDTOSize][intListTemp];
        for(int i=0;i<listRespGroupAksesDTOSize;i++){
            map = GlobalFunction.convertClassToObject(reportAksesDTOList.get(i));
            for(int j=0;j<intListTemp;j++){
                strBody[i][j] = String.valueOf(map.get(loopDataArr[j]));
            }
        }
        new ExcelWriter(strBody,headerArr,"sheet-1",response);
    }

    @Override
    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> token = GlobalFunction.extractToken(request);
        List<Akses> aksesList = null;
        switch (column){
            case "nama":aksesList= aksesRepo.findByNamaContainsIgnoreCase(value);break;
//            case "group":menuList= menuRepo.cariGroupAkses(value);break;
            default:aksesList= aksesRepo.findAll();break;
        }
        /** menggunakan response karena sama untuk report */
        List<ReportAksesDTO> reportAksesDTOList = convertToListReportAksesDTO(aksesList);
        int intReportAksesDTOList = reportAksesDTOList.size();
        if(reportAksesDTOList.isEmpty()){
            GlobalResponse.manualResponse(response,GlobalResponse.dataTidakDitemukan(request));
            return;
        }

        /** INI OBJECT MAP FINAL */
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new ReportAksesDTO());
        List<String> listTemp = new ArrayList<>();
        List<String> listHelper = new ArrayList<>();
        for (Map.Entry<String,Object> entry : mapColumnName.entrySet()) {
            listTemp.add(GlobalFunction.camelToStandar(entry.getKey()));
            listHelper.add(entry.getKey());
        }
        Map<String,Object> mapTemp = null;
        List<Map<String,Object>> listMap = new ArrayList<>();
        for(int i=0;i<reportAksesDTOList.size();i++){
            mapTemp = GlobalFunction.convertClassToObject(reportAksesDTOList.get(i));
            listMap.add(mapTemp);
        }

        map.put("title","REPORT DATA AKSES");
        map.put("listKolom",listTemp);
        map.put("listHelper",listHelper);
        map.put("timestamp",new Date());
        map.put("totalData",intReportAksesDTOList);
        map.put("listContent",listMap);
        map.put("username",token.get("namaLengkap"));
        context.setVariables(map);
        strHtml = springTemplateEngine.process("global-report",context);
        pdfGenerator.htmlToPdf(strHtml,"akses",response);
    }

    public List<RespAksesDTO> convertToListRespAksesDTO(List<Akses> aksesList){
        return modelMapper.map(aksesList,new TypeToken<List<RespAksesDTO>>(){}.getType());
    }
    public List<ReportAksesDTO> convertToListReportAksesDTO(List<Akses> aksesList){
        return modelMapper.map(aksesList,new TypeToken<List<ReportAksesDTO>>(){}.getType());
    }

    public Akses convertToAkses(ValAksesDTO aksesDTO){
        return modelMapper.map(aksesDTO,Akses.class);
    }
    public List<TableAksesDTO> convertToTableAksesDTO(List<Akses> aksesList){
        List<TableAksesDTO> tableAksesDTOList = new ArrayList<>();
        List<TableMenuDTO> list = null;
        TableAksesDTO tableAksesDTO = null;
        for(Akses akses : aksesList){
            tableAksesDTO = new TableAksesDTO();
            tableAksesDTO.setId(akses.getId());
            tableAksesDTO.setNama(akses.getNama());
            tableAksesDTOList.add(tableAksesDTO);
        }
        return tableAksesDTOList;
    }
//    public List<TableAksesDTO> convertToTableAksesDTO(List<Akses> aksesList){
//        List<TableAksesDTO> tableAksesDTOList = new ArrayList<>();
//        List<TableMenuDTO> list = null;
//        TableAksesDTO tableAksesDTO = null;
//        for(Akses akses : aksesList){
//            tableAksesDTO = new TableAksesDTO();
//            tableAksesDTO.setId(akses.getId());
//            tableAksesDTO.setNama(akses.getNama());
//            List<Menu> ltMenu = akses.getLtMenu();
//            List<TableMenuDTO> ltTableMenuDTO = new ArrayList<>();
//            TableMenuDTO tableMenuDTO = null;
//            for(Menu menu : ltMenu){
//                tableMenuDTO = new TableMenuDTO();
//                tableMenuDTO.setId(menu.getId());
//                tableMenuDTO.setNama(menu.getNama());
//                ltTableMenuDTO.add(tableMenuDTO);
//            }
//            tableAksesDTO.setLtMenu(ltTableMenuDTO);
//            tableAksesDTOList.add(tableAksesDTO);
//        }
//        return tableAksesDTOList;
//    }
}