package com.icia.board.controller;

import com.icia.board.dto.MemberDto;
import com.icia.board.service.MemberServcie;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
//    @Autowired
    private final MemberServcie mSer;
    @GetMapping("/login")
    public String login(){
        return "member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto, HttpSession session){
        log.info("id:{}, pwd:{}",memberDto.getM_id(),memberDto.getM_pwd());
        //DB에서 select
        MemberDto mDto = new MemberDto();
        //MemberDto memberDto = new MemberDto();
        //MemberDto.setM_id(m_id).setM_pwd(m_pwd);
//        MemberDto memberDto = MemberDto.builder().m_id(m_id).m_pwd(m_pwd).build();
        boolean result =mSer.login(memberDto);
        if(result){
            session.setAttribute("id",memberDto.getM_id());

            return "redirect:/";
        }
        return "index";
    }
    @GetMapping("/join")
    public String join(){
        return "member/join";
    }
    @PostMapping("/join")
    public String join(MemberDto memberDto, Model model, RedirectAttributes rttr){
        log.info("========memberDto:{}",memberDto);
        boolean result=mSer.join(memberDto);
        if(result){
//            model.addAttribute("msg","가입성공");
            rttr.addFlashAttribute("msg","가입성공");
            return "redirect:/";
        }
        rttr.addFlashAttribute("msg","가입실패");
        return "redirect:/member/join";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}
