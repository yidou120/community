package com.edou.community.community.controller;

import com.edou.community.community.dto.PaginationDTO;
import com.edou.community.community.dto.QuestionDTO;
import com.edou.community.community.model.User;
import com.edou.community.community.service.QuestionService;
import com.sun.media.sound.SoftLowFrequencyOscillator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 中森明菜
 * @create 2019-09-27 21:39
 */
@Controller
public class ProfileController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size,
                          HttpServletRequest request) {
        if (action.equals("questions")) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if (action.equals("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        User user = (User)request.getSession().getAttribute("user");
        PaginationDTO paginationDTO = questionService.findQuestionByUser(user.getAccountId(), page, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
}
