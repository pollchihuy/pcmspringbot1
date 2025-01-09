package com.example.pcmspringbot1.service;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.core.IReportForm;
import com.example.pcmspringbot1.core.IService;
import com.example.pcmspringbot1.dto.response.RespGroupMenuDTO;
import com.example.pcmspringbot1.handler.ResponseHandler;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.repo.GroupMenuRepo;
import com.example.pcmspringbot1.repo.PesertaRepo;
import com.example.pcmspringbot1.util.LoggingFile;
import com.example.pcmspringbot1.util.TransformPagination;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<RespGroupMenuDTO> convertToListRespGroupMenuDTO(List<GroupMenu> groupMenuList){
        return modelMapper.map(groupMenuList,new TypeToken<List<RespGroupMenuDTO>>(){}.getType());
    }
}