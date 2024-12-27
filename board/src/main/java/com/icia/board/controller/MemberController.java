package com.icia.board.controller;

import com.icia.board.dto.MemberDto;
import com.icia.board.service.MemberServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberServcie mSer;
    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }
    @PostMapping("/member/login")
    public String login(@RequestParam String m_id,@RequestParam String m_pwd){
        log.info("id:{}, pwd:{}",m_id,m_pwd);
        //DB에서 select
        MemberDto mDto = new MemberDto();
        //MemberDto memberDto = new MemberDto();
        //MemberDto.setM_id(m_id).setM_pwd(m_pwd);
        MemberDto memberDto = MemberDto.builder().m_id(m_id).m_pwd(m_pwd).build();
        boolean result =mSer.login(memberDto);
        if(result){
            return "board/list";
        }



        return "index";
    }
}
