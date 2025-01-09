package com.icia.board.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor //mybatis의 resultType, @ModelAttribute에서는 반드시 디폴트 생성자가 필요함
public class BoardFile {
    private long bf_num;
    private long bf_bnum;
    private String bf_orifilename;
    private String bf_sysfilename;

}
