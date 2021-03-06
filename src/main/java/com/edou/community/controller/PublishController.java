package com.edou.community.controller;

import com.edou.community.dto.QuestionDTO;
import com.edou.community.mapper.QuestionMapper;
import com.edou.community.model.Question;
import com.edou.community.model.User;
import com.edou.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 中森明菜
 * @create 2019-09-22 19:03
 */
@Controller
public class PublishController {
    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam("id") Integer id,
                            HttpServletRequest request,
                            Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(Integer.parseInt(user.getAccountId()));
        question.setId(id);
        questionService.updateOrCreate(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,
                       Model model){
        QuestionDTO questionById = questionService.findQuestionById(id);
        model.addAttribute("title",questionById.getTitle());
        model.addAttribute("description",questionById.getDescription());
        model.addAttribute("tag",questionById.getTag());
        model.addAttribute("id",questionById.getId());
        return "publish";
    }
}
