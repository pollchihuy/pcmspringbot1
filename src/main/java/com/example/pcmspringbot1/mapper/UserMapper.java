//package com.example.pcmspringbot1.mapper;
//
//import com.example.pcmspringbot1.dto.response.RespAksesDTO;
//import com.example.pcmspringbot1.dto.response.UserRespDTO;
//import com.example.pcmspringbot1.model.User;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapper {
//
//    public UserRespDTO userToUserRespDTO(User user) {
//        UserRespDTO userRespDTO = new UserRespDTO();
//        userRespDTO.setAlamat(user.getAlamat());
//        userRespDTO.setEmail(user.getEmail());
//        userRespDTO.setId(user.getId());
//        userRespDTO.setUsername(user.getUsername());
//        userRespDTO.setNoHp(user.getNoHp());
//        RespAksesDTO aksesDTO = new RespAksesDTO();
//        aksesDTO.setId(user.getAkses().getId());
//        aksesDTO.setNama(user.getAkses().getNama());
//        userRespDTO.setAkses(aksesDTO);
//        return userRespDTO;
//    }
//}
