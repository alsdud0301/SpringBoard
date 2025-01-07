package com.icia.springtest.service;

import com.icia.springtest.dao.ProductDao;
import com.icia.springtest.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductDao pDao;
    public List<ProductDto> getProductList(ProductDto pDto) {
//        Map<String,Integer> pageMap = new HashMap<>();
//        pageMap.put("startIndex")

        return pDao.getProductList(pDto);
    }
}
