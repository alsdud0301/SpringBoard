package com.icia.springtest.controller;

import com.icia.springtest.dto.ProductDto;
import com.icia.springtest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final ProductService pSer;
    @GetMapping
    public String board(ProductDto pDto, Model model){

        List<ProductDto> pList=null;
        pList= pSer.getProductList(pDto);

        if(pList!=null){
            model.addAttribute("pList",pList);
            return "/board";
        }
        return "redirect :/";



    }

}
