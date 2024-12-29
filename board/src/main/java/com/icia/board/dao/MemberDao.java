package com.icia.board.dao;
//DB FW: ibatis -->mubatis

import com.icia.board.dto.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {
//    @Select()
    boolean login(MemberDto memberDto);

//    @Insert()
    boolean join(MemberDto memberDto);

//    @Select("select count(*) from member where m_id=#{mId}")
    boolean isUserId(String mId);
}
