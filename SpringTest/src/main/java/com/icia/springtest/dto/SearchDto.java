package com.icia.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class SearchDto {
    private int start;
    private int end;
    private Integer pageNum;
    private String keyword;
    private String searchtype;



}
