package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.core.IReportForm;
import com.example.pcmspringbot1.core.IService;
import com.example.pcmspringbot1.handler.ResponseHandler;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.repo.GroupMenuRepo;
import com.example.pcmspringbot1.repo.PesertaRepo;
import com.example.pcmspringbot1.util.LoggingFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private StringBuilder sbuild = new StringBuilder();


    @Override
    public ResponseEntity<Object> save(GroupMenu groupMenu, HttpServletRequest request) {
        try{
            int x = 1/0;
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
    public ResponseEntity<Object> update(Long id, GroupMenu groupMenu, HttpServletRequest request) {
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
    public List<GroupMenu> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long userId) {
        return List.of();
    }

    @Override
    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response) {

    }
}
