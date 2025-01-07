package com.icia.board.dto;

import lombok.*;


//@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReplyDto {
    private Integer r_bnum;
    private String r_contents;
    private String r_writer;
}
