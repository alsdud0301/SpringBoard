package com.icia.board.dao;

import com.icia.board.dto.ReplyDto;
import com.icia.board.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import com.icia.board.dto.BoardDto;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {

    void insertDummyData(BoardDto boardDto);

    ArrayList<BoardDto> getBoardList(Map<String, Integer> pageMap);

    @Select("select * from board")
    List<BoardDto> getBoardListAll(Map<String, Integer> pageMap);


    int getBoardCount(SearchDto sDto);


    List<BoardDto> getBoardListSearch(SearchDto sDto);

    BoardDto getBoardDetail(Integer bNum);

    boolean deleteBoard(Integer bNum);

    boolean insertReplys(ReplyDto rDto);

    List<ReplyDto> getReplyList(Integer bNum);

    boolean insertContent(BoardDto board);
}
