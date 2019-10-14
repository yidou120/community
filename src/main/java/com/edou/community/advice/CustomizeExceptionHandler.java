package com.edou.community.advice;

import com.edou.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 中森明菜
 * @create 2019-10-13 20:00
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView getHandle(Throwable ex, Model model){
        if(ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", "服务器冒烟了");
        }
        return new ModelAndView("error");
    }
}
