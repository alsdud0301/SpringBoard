package com.icia.springtest.service;

import com.icia.springtest.dao.ProductDao;
import com.icia.springtest.dto.FileDto;
import com.icia.springtest.dto.ProductDto;
import com.icia.springtest.dto.ReplyDto;
import com.icia.springtest.dto.SearchDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    public boolean insertProduct(ProductDto pDto, String uploadsDirectory) {
        if(pDao.insertProduct(pDto)){
            if(!pDto.getAttachments().get(0).isEmpty()){
                File dir = new File(uploadsDirectory);
                if(!dir.exists()){
                    dir.mkdir();
                }

                for(MultipartFile multipartFile : pDto.getAttachments()){
                    String pf_originalname = multipartFile.getOriginalFilename();
                    String pf_systemname = UUID.randomUUID() + "." + FilenameUtils.getExtension(pf_originalname);

                    try{
                        multipartFile.transferTo(new File(uploadsDirectory+pf_systemname));
                        if(!pDao.FileInsert(new FileDto(pDto.getT_num(), pf_systemname))){
                            return false;
                        }
                    }catch(IOException e){
                        throw  new RuntimeException(e);
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }

    }

    public ProductDto getProductInfo(Integer tNum) {
        return pDao.getProductInfo(tNum);
    }

    public boolean insertReply(ReplyDto rDto) {
        return pDao.insertReply(rDto);
    }

    public ResponseEntity<Resource> fileDownload(FileDto fDto, HttpSession session) throws IOException {

        return pDao.fileDownload(fDto,session);
    }
}
