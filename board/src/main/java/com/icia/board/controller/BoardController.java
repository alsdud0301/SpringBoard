package com.icia.board.controller;

import com.icia.board.dto.BoardDto;
import com.icia.board.dto.MemberDto;
import com.icia.board.dto.ReplyDto;
import com.icia.board.dto.SearchDto;
import com.icia.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
//    @Autowired
    private final BoardService bSer;

    //localhost/board/ or localhost/board
    //localhost/board?pageNum=2
    @GetMapping
    public String list(SearchDto sDto, Model model){
        log.info("=====before sDto:{}",sDto);
        //서비스-->DB -->게시글들
        if(sDto.getPageNum()==null){
            sDto.setPageNum(1);
        }
        if(sDto.getListcnt()==null){
            sDto.setListcnt(BoardService.LISTCNT);
        }
        if(sDto.getStartIdx()==null){
            sDto.setStartIdx(0);
        }
        List<BoardDto> bList=null;
//        if(sDto.getColname()==null || sDto.getKeyword()==null){
//           bList=bSer.getBoardList(sDto.getPageNum()); //페이징 클릭
//        }else{
//           bList=bSer.getBoardList(sDto);
//        }

        //동적 쿼리 작성시
        //검색 또는 페이지 번호 버튼 클릭 시
        bList = bSer.getBoardListSearchNew(sDto);


        if(bList!=null){
            //페이지 정보

            String pageHtml  = bSer.getPaging(sDto);
            model.addAttribute("paging",pageHtml);
            model.addAttribute("bList",bList); //js(json), each문

            return "board/list";
        }
        return "redirect:/";
    }

    @GetMapping("/write")
    public  String write(){
        return "board/write";
    }

    @PostMapping("/write")
    public String write(BoardDto board){
        //DB에 글을 저장
        return "redirect:/board/list"; //get만 허용
    }
//    @GetMapping("/detail/{bnum}")
//    public String detail(@PathVariable("bnum") Integer bnum, Model model){
//        log.info("===con bnum:{}",bnum);
//        return null;
//    }
    @GetMapping("/detail")
    public String detailParam(@RequestParam("b_num") Integer b_num,Model model){
        log.info("===con bnum:{}",b_num);
        if(b_num==null){
            return "redirect:/board";
        }
         BoardDto board=bSer.getBoardDetail(b_num);
        log.info("=======board:{}",board);
        if(board==null){
            return "redirect:/board";
        }else {
            model.addAttribute("board",board);
            return "board/detail";
        }

    }

    @GetMapping("/delete")
    public String boardDelete(@RequestParam("b_num") Integer b_num, Model model, RedirectAttributes rttr){
        log.info("====delete bnum:{}",b_num);
        if(b_num==null || b_num<1){
            return "redirect:/board";
        }
        if(bSer.boardDelete(b_num)){
            rttr.addFlashAttribute("msg",b_num+"번 삭제 성공"); //1번만 출력
            //다음요청의 request객체에 저장됨, 여러번 출력
//            rttr.addAttribute("msg",b_num+"번 삭제 성공");
            return "redirect:/board";
        }else{
            rttr.addAttribute("msg",b_num+"번 삭제 실패");
            return "redirect:/board/detail?b_num="+b_num;
        }


    }
    @PostMapping("/reply")
    @ResponseBody
    public String insertReply(@RequestBody ReplyDto rDto, HttpSession session){
        log.info("===insert r_bnum:{}",rDto.getR_bnum());
        log.info("===insert r_contents:{}",rDto.getR_contents());
        log.info("===insert r_writer:{}",((MemberDto)session.getAttribute("member")).getM_id());
        return "성공";
    }
}
