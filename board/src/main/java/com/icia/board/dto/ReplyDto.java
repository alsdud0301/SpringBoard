package com.icia.board.dto;

import lombok.*;

import java.time.LocalDate;


//@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReplyDto {

    private Integer r_id;
    private String r_content;
    private LocalDate r_date;
    private String m_id;
    private Integer b_num;
}
