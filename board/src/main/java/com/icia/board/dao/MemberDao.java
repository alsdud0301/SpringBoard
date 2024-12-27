package com.icia.board.dao;
//DB FW: ibatis -->mubatis

import com.icia.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {
//    @Select()
    boolean login(MemberDto memberDto);
}
