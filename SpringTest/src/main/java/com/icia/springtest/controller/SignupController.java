package com.icia.springtest.controller;

import com.icia.springtest.dto.UserDto;
import com.icia.springtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignupController
{

    private final UserService uSer;
    @PostMapping("/joincheck")
    @ResponseBody
    public boolean chekcid(@RequestBody UserDto uDto) {
        log.info("아이디: {}", uDto.getUserID());
        log.info("postmapping반응");

        boolean result = uSer.checkID(uDto);
        if(result){
            return true;
        }else{
            return false;
        }

    }
}
