package com.icia.board.service;

import com.icia.board.dao.MemberDao;
import com.icia.board.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MemberServcie {
    @Autowired
    private MemberDao mDao;

    public boolean login(MemberDto memberDto) {

        return mDao.login(memberDto);
    }
}
