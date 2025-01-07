package com.icia.board.service;

import com.icia.board.dao.MemberDao;
import com.icia.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServcie {
    @Autowired
    private MemberDao mDao;

    public MemberDto login(MemberDto memberDto) {
        String encoPw = mDao.getSecurityPw(memberDto.getM_id());
        log.info("encoPw:{}",encoPw);
        if(encoPw!=null){
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            log.info("========아이디 존재함");
            if(pwEncoder.matches(memberDto.getM_pwd(),encoPw)){
                log.info("로그인 성공");
                return mDao.getMemberInfo(memberDto.getM_id());
            }else {
                log.info("비번 오류");
                return null;
            }

        }else{
            log.info("========아이디 오류");
            return null;
        }

    }

    public boolean join(MemberDto memberDto) {
        //Encoder(암호화) <---> Decoder(복호화)


        //이미 사용중인 아이디: true
        if(mDao.isUserId(memberDto.getM_id())){
            return false;
        };
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setM_pwd(pwEncoder.encode(memberDto.getM_pwd()));
        return  mDao.join(memberDto);
    }
}
