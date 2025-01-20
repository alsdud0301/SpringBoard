package com.icia.springtest.controller;

import com.icia.springtest.dto.*;
import com.icia.springtest.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final ProductService pSer;
    private FileDto fileDto;

    @GetMapping
    public String board(SearchDto sDto, Model model) {

        List<ProductDto> pList = null;
        pList = pSer.getProductList(sDto);
//        log.info("페이지넘버"+pageNum);


        if (pList != null) {

            model.addAttribute("pList", pList);
            model.addAttribute("file", fileDto);
            return "board";
        }
        return "redirect:/";
    }

    @GetMapping("/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writecontent(@ModelAttribute ProductDto pDto, HttpSession session, RedirectAttributes rttr) {
        pDto.setT_user((String) session.getAttribute("userID"));
        boolean result = pSer.insertProduct(pDto, session.getServletContext().getRealPath("/") + "uploads/");


        if (result) {

            return "redirect:/board?pageNum=1";
        } else {
            return "board/write";
        }

    }


    @PostMapping("/reply")
    @ResponseBody
    public ReplyDto replypost(@RequestBody ReplyDto rDto) {
        log.info("결과" + rDto);
        boolean result = pSer.insertReply(rDto);
        if (result) {
            return rDto;
        } else {
            return null;
        }
    }

    @GetMapping("/reply/{r_tnum}")
    @ResponseBody
    public List<ReplyDto> reply(@PathVariable int r_tnum) {
//        log.info("결과"+rDto);
        List<ReplyDto> rList = null;
        rList = pSer.getReply(r_tnum);
        if (rList != null) {
            log.info("테스트" + rList);
            return rList;
        } else {
            return null;
        }
    }

    @GetMapping("/detail")
    public String productDetail(@RequestParam("t_num") Integer t_num, Model model) {
        ProductDto pDto = pSer.getProductInfo(t_num);
        FileDto fileDto = pSer.getFile(t_num);
        log.info(String.valueOf(fileDto));
        if (pDto != null || fileDto != null) {
            model.addAttribute("product", pDto);
            model.addAttribute("file", fileDto);

            return "board/detail";
        } else {
            return "redirect:/board";
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(FileDto fDto, HttpSession session, @RequestParam("t_num") Integer t_num) {
        try {
            ResponseEntity<Resource> resp = pSer.fileDownload(fDto, session, t_num);

            return resp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
//    @GetMapping("/update")
//    @ResponseBody
//    public ResponseEntity<?> updateinfo(@RequestParam int t_num){
//        ProductDto result = pSer.getProductInfo(t_num);
//        log.info("테스트 : "+result);
//        if(result!=null){
////            log.info("테스트 : "+result);
//            return ResponseEntity.ok(result);
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터를 찾을 수 없습니다.");
//        }
//    }


    @GetMapping("/update")
    public String update() {
        return "board/update";
    }

    @PostMapping("/update2")
    @ResponseBody
    public ResponseDto updateinfo(@RequestBody ProductDto pDto) {
        log.info("t_num" + pDto.getT_num());
        ProductDto result = pSer.getProductupdateInfo(pDto.getT_num());
        FileDto result2 = pSer.getFileupdate(pDto.getT_num());
        log.info("테스트 : " + result);

        ResponseDto response = new ResponseDto();
        response.setProductDto(result);
        response.setFileDto(result2);
        return response;
    }

    @PostMapping("/update")
    @ResponseBody
    public boolean updatePost(ProductDto pDto, HttpSession session) {
        log.info("수정 테스트");
        boolean result = pSer.updateProduct(pDto,session.getServletContext().getRealPath("/") + "uploads/");
        log.info(String.valueOf(result));
        if (result) {
            return true;
        } else {
            return false;
        }
    }
//    @PostMapping("/delete")
//    @ResponseBody
//    public boolean delete(@RequestBody ProductDto pDto){
//        log.info("돌아가라");
//        boolean result = pSer.deleteProduct(pDto.getT_num());
//        boolean result2 = pSer.deleteFile(pDto.getT_num());
//        log.info("삭제 컨트롤러 확인");
//        if(result || result2){
//            return true;
//        }else{
//            return false;
//        }
//    }
    @DeleteMapping("/delete/{t_num}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable int t_num) {
        log.info("돌아가라");
        boolean result = pSer.deleteProduct(t_num);
        boolean result2 = pSer.deleteFile(t_num);
        log.info("삭제 컨트롤러 확인");
        if (result || result2) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터를 찾을 수 없습니다.");
        }
    }
}



