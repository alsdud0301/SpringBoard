package com.icia.springtest.controller;

import com.icia.springtest.dto.UserDto;
import com.icia.springtest.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService uSer;
    @GetMapping("/join")
    public String join(){

        return "user/join";
    }
    @PostMapping("/join")
    public String join(UserDto userDto){
        boolean result=uSer.join(userDto);
        if(result){
            return "redirect:/";
        }else{
            return "redirect:/user/join";
        }

    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, HttpSession session){
        boolean result = uSer.login(userDto);
        log.info("result"+result);
        if(result){
            session.setAttribute("userID",userDto.getUserID());
            session.setMaxInactiveInterval(60*30);
            log.info("넘어가라");
            return "redirect:/board";
        }else{
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes rttr){
        session.invalidate();
        rttr.addFlashAttribute("msg","로그아웃성공");
        return "redirect:/";
    }


}
