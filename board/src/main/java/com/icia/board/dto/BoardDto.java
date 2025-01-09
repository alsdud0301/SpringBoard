package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BoardDto {
    private int b_num;
    private String b_title;
    private String b_contents;
    private String b_writer;
    private LocalDateTime b_date;
    private List<MultipartFile> attachments;
    private int b_views;
}