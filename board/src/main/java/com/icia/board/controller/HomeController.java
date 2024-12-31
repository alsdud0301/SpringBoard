package com.icia.board.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String home(HttpSession session){
        //세션의 불필요한 속성 객체 삭제
        if(session.getAttribute("urlPrior_login")!=null){
            session.removeAttribute("urlPrior_login");
        }
        return "index";
    }
}
