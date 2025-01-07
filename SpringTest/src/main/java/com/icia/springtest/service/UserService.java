package com.icia.springtest.service;

import com.icia.springtest.dao.UserDao;
import com.icia.springtest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserDao uDao;
    public boolean join(UserDto userDto) {
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        userDto.setUserPW(pwEncoder.encode(userDto.getUserPW()));
        log.info(userDto.getUserID());
        log.info("user : "+userDto.getUserPW());
        return  uDao.join(userDto);
    }

    public boolean login(UserDto userDto) {
        log.info("아이디 : "+userDto.getUserID());
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String encoPW = uDao.getSecurityPw(userDto.getUserID());
        log.info("비번"+encoPW);
        log.info("비번2");
        log.info(String.valueOf(pwEncoder.matches(userDto.getUserPW(),encoPW)));
        if(pwEncoder.matches(userDto.getUserPW(),encoPW)){
            log.info("로그인 성공");
            return true;
        }else{
            log.info("로그인 실패");
            return false;
        }


    }

    public boolean checkID(UserDto uDto) {
        String checkid = uDao.login(uDto.getUserID());
        log.info("checkID반응");
        if(checkid!=null){
            log.info("true반환");
            return true;
        }else{
            log.info("false반환");
            return false;
        }

    }


}
