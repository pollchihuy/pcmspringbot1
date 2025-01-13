package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.core.IReportForm;
import com.example.pcmspringbot1.core.IService;
import com.example.pcmspringbot1.dto.response.RespGroupMenuDTO;
import com.example.pcmspringbot1.model.DataCoba;
import com.example.pcmspringbot1.model.DataCoba;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.repo.DataCobaRepo;
import com.example.pcmspringbot1.repo.GroupMenuRepo;
import com.example.pcmspringbot1.util.ExcelWriter;
import com.example.pcmspringbot1.util.GlobalFunction;
import com.example.pcmspringbot1.util.PdfGenerator;
import com.example.pcmspringbot1.util.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataCobaService implements IService<DataCoba>, IReportForm<DataCoba> {

    @Autowired
    private DataCobaRepo dataCobaRepo;

    @Autowired
    private GroupMenuRepo groupMenuRepo;

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private TransformPagination transformPagination;

    private StringBuilder sbuild = new StringBuilder();

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public ResponseEntity<Object> save(DataCoba dataCoba, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, DataCoba dataCoba, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> uploadDataExcel(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<DataCoba> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long userId) {
        return List.of();
    }

    @Override
    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<DataCoba> dataCobaList = null;
        switch (column){
            case "uuid":dataCobaList= dataCobaRepo.findByUuidContainsIgnoreCase(value);break;
            case "duit":dataCobaList= dataCobaRepo.cariDuit(value);break;
            case "nama-depan":dataCobaList= dataCobaRepo.findByNamaDepanContainsIgnoreCase(value);break;
            default:dataCobaList= dataCobaRepo.findAll();break;
        }

        sbuild.setLength(0);
        String headerKey = "Content-Disposition";
        sbuild.setLength(0);

        String headerValue = sbuild.append("attachment; filename=data-coba_").
                append(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss.SSS").format(new Date())).append(".xlsx").toString();
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/octet-stream");

        Map<String,Object> map = GlobalFunction.convertClassToObject(new DataCoba());
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
        int listRespDataCobaDTOSize = dataCobaList.size();
        String [][] strBody = new String[listRespDataCobaDTOSize][intListTemp];
        for(int i=0;i<listRespDataCobaDTOSize;i++){
            map = GlobalFunction.convertClassToObject(dataCobaList.get(i));
            for(int j=0;j<intListTemp;j++){
                strBody[i][j] = String.valueOf(map.get(loopDataArr[j]));
            }
        }
        new ExcelWriter(strBody,headerArr,"sheet-1",response);
    }
    public void downloadReportExcelRaw(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<DataCoba> dataCobaList = null;
        switch (column){
            case "uuid":dataCobaList= dataCobaRepo.findByUuidContainsIgnoreCase(value);break;
            case "duit":dataCobaList= dataCobaRepo.cariDuit(value);break;
            case "nama-depan":dataCobaList= dataCobaRepo.findByNamaDepanContainsIgnoreCase(value);break;
            default:dataCobaList= dataCobaRepo.findAll();break;
        }
    }

    @Override
    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<DataCoba> dataCobaList = null;
        switch (column){
            case "uuid":dataCobaList= dataCobaRepo.findByUuidContainsIgnoreCase(value);break;
            case "duit":dataCobaList= dataCobaRepo.cariDuit(value);break;
            case "nama-depan":dataCobaList= dataCobaRepo.findByNamaDepanContainsIgnoreCase(value);break;
            default:dataCobaList= dataCobaRepo.findAll();break;
        }
        int intRespGroupMenuDTOList = dataCobaList.size();

        if(dataCobaList.isEmpty()){
            return;
        }

        /** INI OBJECT MAP FINAL */
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new DataCoba());
        List<String> listTemp = new ArrayList<>();
        List<String> listHelper = new ArrayList<>();
        for (Map.Entry<String,Object> entry : mapColumnName.entrySet()) {
            listTemp.add(GlobalFunction.camelToStandar(entry.getKey()));
            listHelper.add(entry.getKey());
        }
        Map<String,Object> mapTemp = null;
        List<Map<String,Object>> listMap = new ArrayList<>();
//        for(int i=0;i<dataCobaList.size();i++){
        for(int i=0;i<5000;i++){
            mapTemp = GlobalFunction.convertClassToObject(dataCobaList.get(i));
            listMap.add(mapTemp);
        }
        map.put("title","REPORT DATA COBA");
        map.put("listKolom",listTemp);
        map.put("listHelper",listHelper);
        map.put("timestamp",new Date());
        map.put("totalData",intRespGroupMenuDTOList);
        map.put("listContent",listMap);
        map.put("username","Paul");
        context.setVariables(map);
        strHtml = springTemplateEngine.process("global-report",context);
        pdfGenerator.htmlToPdf(strHtml,"data-coba",response);
    }
}
