package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {
    @GetMapping("/board/list")
    public String list(){
        System.out.println("list목록보기");
        return "read";
    }
    @GetMapping("/board/read/{no}")
    public String read(){
        System.out.println("글 한개 읽기");
        return "read";
    }
    @GetMapping("/board/write")
    public String write(){
        System.out.println("글쓰기");
        return null;
    }
}
