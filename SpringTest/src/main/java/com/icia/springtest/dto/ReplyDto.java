package com.icia.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private int r_num;
    private int r_tnum;
    private String r_contents;
    private String r_writer;
}
