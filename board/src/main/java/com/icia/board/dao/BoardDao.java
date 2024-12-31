package com.icia.board.dao;

import org.apache.ibatis.annotations.Mapper;

import com.icia.board.dto.BoardDto;

@Mapper
public interface BoardDao {

    void insertDummyData(BoardDto boardDto);
}
