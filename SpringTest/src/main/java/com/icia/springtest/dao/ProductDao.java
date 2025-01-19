package com.icia.springtest.dao;


import com.icia.springtest.dto.FileDto;
import com.icia.springtest.dto.ProductDto;
import com.icia.springtest.dto.ReplyDto;
import com.icia.springtest.dto.SearchDto;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface ProductDao {


    List<ProductDto> getProductList(SearchDto pDto);

    boolean insertProduct(ProductDto pDto);

    @Select("select * from test where t_num=#{tNum}")
    ProductDto getProductInfo(Integer tNum);

    boolean FileInsert(FileDto fileDto);

    boolean insertReply(ReplyDto rDto);

    ResponseEntity<Resource> fileDownload(FileDto fDto, HttpSession session, Integer t_num);

    FileDto getFile(Integer tNum);

    List<ReplyDto> getReply(Integer tNum);

    boolean updateProduct(ProductDto pDto);

    ProductDto getupdateinfo(Integer tNum);

    FileDto getupdateFile(Integer tNum);

    boolean FileUpdate(FileDto fileDto);
}

