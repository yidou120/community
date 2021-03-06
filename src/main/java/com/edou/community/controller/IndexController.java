package com.edou.community.controller;

import com.edou.community.dto.PaginationDTO;
import com.edou.community.mapper.UserMapper;
import com.edou.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 中森明菜
 * @create 2019-09-21 17:02
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public String index(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        HttpServletRequest request,
                        Model model){
        //获取发表问题数据
        PaginationDTO paginationDTO = questionService.selectAll(page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}
