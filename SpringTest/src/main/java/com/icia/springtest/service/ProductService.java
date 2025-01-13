package com.icia.springtest.service;

import com.icia.springtest.dao.ProductDao;
import com.icia.springtest.dto.ProductDto;
import com.icia.springtest.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Autowired
    private ProductDao pDao;

    public List<ProductDto> getProductList(SearchDto sDto) {
        if(sDto.getPageNum()!=null){
            log.info("서비스 실행중");
                if(sDto.getPageNum()>0){
                    sDto.setEnd(10);
                   sDto.setStart(sDto.getEnd()*(sDto.getPageNum()-1));
                }


        }
        return pDao.getProductList(sDto);
    }

    public boolean insertProduct(ProductDto pDto) {
        return pDao.insertProduct(pDto);
    }
}
