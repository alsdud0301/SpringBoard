package com.icia.springtest.controller;

import com.icia.springtest.dto.UserDto;
import com.icia.springtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService uSer;
    @GetMapping("/join")
    public String join(){

        return "user/join";
    }
    @PostMapping("/user/join")
    public String join(UserDto userDto){
        boolean result=uSer.join(userDto);
        if(result){
            return "redirect :user/login";
        }else{
            return "redirect :user/join";
        }

    }

    @GetMapping("/user/login")
    public String login(){
        return "/board";
    }

    @PostMapping("/user/login")
    public String login(UserDto userDto){
        boolean result = uSer.login(userDto);
        if(result){
            return "redirect :/board";
        }else{
            return "redirect :/user/login";
        }
    }


}
