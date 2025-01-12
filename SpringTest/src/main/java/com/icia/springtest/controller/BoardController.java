package com.icia.springtest.controller;

import com.icia.springtest.dto.ProductDto;
import com.icia.springtest.dto.SearchDto;
import com.icia.springtest.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final ProductService pSer;
    @GetMapping
    public String board(SearchDto sDto, Model model){

        List<ProductDto> pList=null;
        pList= pSer.getProductList(sDto);
//        log.info("페이지넘버"+pageNum);


        if(pList!=null){
            
            model.addAttribute("pList",pList);
            return "board";
        }
        return "redirect:/";
    }
    @GetMapping("/write")
    public String write(){
        return "/board/write";
    }
    @PostMapping("/write")
    @ResponseBody
    public boolean writecontent(@RequestBody ProductDto pDto){

        boolean result  = pSer.insertProduct(pDto);
        if(result){

            return true;
        }else{
            return false;
        }

    }

//    GetMapping()
//    public String boardPaging(ProductDto pDto){
//
//        return "";
//    }

}
