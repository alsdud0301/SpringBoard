package com.icia.springtest.dao;


import com.icia.springtest.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {


    List<ProductDto> getProductList(ProductDto pDto);
}

