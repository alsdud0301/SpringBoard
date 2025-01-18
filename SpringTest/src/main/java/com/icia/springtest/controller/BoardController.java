package com.icia.springtest.controller;

import com.icia.springtest.dto.FileDto;
import com.icia.springtest.dto.ProductDto;
import com.icia.springtest.dto.ReplyDto;
import com.icia.springtest.dto.SearchDto;
import com.icia.springtest.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final ProductService pSer;
    private FileDto fileDto;
    @GetMapping
    public String board(SearchDto sDto, Model model){

        List<ProductDto> pList=null;
        pList= pSer.getProductList(sDto);
//        log.info("페이지넘버"+pageNum);


        if(pList!=null){

            model.addAttribute("pList",pList);
            model.addAttribute("file",fileDto);
            return "board";
        }
        return "redirect:/";
    }
    @GetMapping("/write")
    public String write(){
        return "board/write";
    }
    @PostMapping("/write")
    public String writecontent(@ModelAttribute ProductDto pDto, HttpSession session, RedirectAttributes rttr){
        pDto.setT_user((String) session.getAttribute("userID"));
        boolean result  = pSer.insertProduct(pDto,session.getServletContext().getRealPath("/")+"uploads/");


        if(result){

            return "redirect:/board?pageNum=1";
        }else{
            return "board/write";
        }

    }


    @PostMapping("/reply")
    @ResponseBody
    public ReplyDto replypost(@RequestBody ReplyDto rDto){
        log.info("결과"+rDto);
        boolean result = pSer.insertReply(rDto);

        if(result){
            return rDto;
        }else{
            return null;
        }
    }
    @GetMapping("/reply/{r_tnum}")
    @ResponseBody
    public List<ReplyDto> reply(@PathVariable int r_tnum){
//        log.info("결과"+rDto);
        List<ReplyDto> rList = null;
        rList = pSer.getReply(r_tnum);
        if(rList!=null){
            log.info("테스트" + rList);
            return rList;
        }else{
            return null;
        }
    }
    @GetMapping("/detail")
    public String productDetail(@RequestParam("t_num") Integer t_num,Model model){
        ProductDto pDto = pSer.getProductInfo(t_num);
        FileDto fileDto = pSer.getFile(t_num);
        if(pDto!=null && fileDto!=null){
            model.addAttribute("product",pDto);
            model.addAttribute("file",fileDto);
            return "board/detail";
        }else{
            return "redirect:/board";
        }
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> download(FileDto fDto,HttpSession session,@RequestParam("t_num") Integer t_num){
        try{
            ResponseEntity<Resource> resp = pSer.fileDownload(fDto,session,t_num);

            return resp;
        }catch(IOException e){
            throw new RuntimeException(e);
        }


    }




}
