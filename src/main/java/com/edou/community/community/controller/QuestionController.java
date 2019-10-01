package com.edou.community.community.controller;

import com.edou.community.community.dto.QuestionDTO;
import com.edou.community.community.model.Question;
import com.edou.community.community.model.User;
import com.edou.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 中森明菜
 * @create 2019-09-29 20:34
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model,
                           HttpServletRequest request){
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("question",questionDTO);
//        User user = (User)request.getSession().getAttribute("user");
//        System.out.println(user.getAccountId());
        return "question";
    }
}
