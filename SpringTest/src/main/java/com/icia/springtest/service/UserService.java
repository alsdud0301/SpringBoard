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
        userDto.setUserPW(pwEncoder.encode(userDto.getUserID()));
        log.info(userDto.getUserID());
        log.info("user : "+userDto.getUserPW());
        return  uDao.join(userDto);
    }

    public boolean login(UserDto userDto) {
        log.info("아이디 : "+userDto.getUserID());
        return uDao.login(userDto);
    }
}
