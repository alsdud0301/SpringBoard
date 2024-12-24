package com.example.demo.controller;

import com.example.demo.dto.DataDto;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {
        @GetMapping
        public String index(/*@RequestParam("id") String id*/){
//            System.out.println("id" + id);
//            log.info("id:{}",id);
            return "index";
        }
        @GetMapping("/intro")
        public String intro(Model model){
            model.addAttribute("currentDate", LocalDate.now());
            return "intro";
        }
        @GetMapping("/t_output")
            public String output (Model model){
            log.info("==================t_output ok");
            model.addAttribute("htmlStr","<h3>안녕?</h3>");
            //map 데이터 묶음
            Map<String,String> map = new HashMap<>();
            map.put("name","유기현");
            map.put("age","30");
            map.put("addr","서울");
            model.addAttribute("mapData",map);
            DataDto dto = new DataDto();
            dto.setName("임창균");
            dto.setAge(29);
            dto.setAddress("서울");
            model.addAttribute("dtoData",dto);
            model.addAttribute("msg","서버로부터의 메세지");


            return "t_output";
        }
//        @RequestMapping(value = "/t_control",method= RequestMethod.GET)
        @GetMapping("/t_control")
    public String control(Model model, HttpSession session){
            session.setAttribute("User_id","admin");
            //Spring 5이후부터 보안때문에 sessoin, request, response 객체를 model에 담아서 thymeleaft로 넘겨야함
        //model.addAttribute("ss",session);
//            session.invalidate();
        model.addAttribute("msg","이문자열이 보입니다");
        model.addAttribute("age",34);
        List<DataDto> list = new ArrayList<>();
        for(int i =0; i<5; i++){
            DataDto dto = new DataDto();
            dto.setName("이름 :"+i);
            dto.setAge(20+i);
            dto.setAddress("주소 :"+i);
            list.add(dto);
        }
        model.addAttribute("list",list);
        return "t_control";
        }
        @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") String id){
            System.out.println("id" + id);
            return null;
        }
        @GetMapping("/sendDate")
        public String sendData(Model model){
            return "sendDate";
        }
        @GetMapping("/a_send")
    public String aSend(Model model,@RequestParam("num1") Integer num1,@RequestParam("num2") Integer num2){
            model.addAttribute("num1",num1);
            model.addAttribute("num2",num2);
            model.addAttribute("result",num1+num2);
            System.out.println("num1 :"+num1);
            System.out.println("num2:"+num2);
            return "a_send";
        }
    @GetMapping("/noneDtoSend")
    public String noneDtoSend(@RequestParam String name,@RequestParam int age,@RequestParam String address,Model model){
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        System.out.println("address : " + address);
        model.addAttribute("result","none dto send ok");
        return "s_result";

    }
    @PostMapping("/dtoSend")
    public  String dtoSend(@ModelAttribute DataDto dto,Model model){
        System.out.println("name : " +dto.getName());
        System.out.println("age : " + dto.getAge());
        System.out.println("address : " + dto.getAddress());
        model.addAttribute("result","dto send Ok");
        return "s_result";
    }
    @GetMapping("/userfrm")
    public String user(Model model){
            model.addAttribute("user", new DataDto("이민혁",31,"서울시"));
            return "user";
    }
    @PostMapping("/user")
    public String user_post(@ModelAttribute DataDto dto,Model model){
        System.out.println("name : "+dto.getName());
        System.out.println("name : "+dto.getAge());
        System.out.println("name : "+dto.getAddress());
        return "user";
    }
}
