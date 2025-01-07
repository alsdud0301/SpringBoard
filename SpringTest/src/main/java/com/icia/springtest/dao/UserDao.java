package com.icia.springtest.dao;


import com.icia.springtest.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {


    boolean join(UserDto userDto);

    String login(String userID);

    String getSecurityPw(String userID);
}
