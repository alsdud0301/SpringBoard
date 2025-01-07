package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Alias("MemberDto")
public class MemberDto {
    private String  m_id; //필드명 ==파라미터명==컬럼명
    private String m_pwd;
    private String m_name;
    private String m_birth;
    private String m_addr;
    private String m_point;
    private String g_name;


}
