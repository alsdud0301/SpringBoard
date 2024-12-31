package com.icia.board.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
@Slf4j
public class SessionInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //localhost/board/list/p=16&size=10
        log.info("=========preHandle call uri={}",request.getRequestURI());
        log.info("=========preHandle call queryString={}",request.getQueryString());
        if(request.getSession().getAttribute("member")==null){
            log.info("=============인터셉트!--로그인 안함");
            request.getSession().setAttribute("urlPrior",request.getRequestURI()+'?'+request.getQueryString());
            response.sendRedirect("/member/login"); //redirect는 get만 요청

            return false; //컨트롤러 진입금지
        }
        return true; //컨트롤러 진입
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("view 직전에 호출");
    }
}
