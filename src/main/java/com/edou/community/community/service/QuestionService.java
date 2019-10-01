package com.edou.community.community.service;

import com.edou.community.community.dto.PaginationDTO;
import com.edou.community.community.dto.QuestionDTO;
import com.edou.community.community.mapper.QuestionMapper;
import com.edou.community.community.mapper.UserMapper;
import com.edou.community.community.model.Question;
import com.edou.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 中森明菜
 * @create 2019-09-24 8:59
 */
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public PaginationDTO selectAll(Integer page, Integer size){
        //查所有问题个数
        Integer totalCount = questionMapper.totalCount();
        PaginationDTO paginationDTO = new PaginationDTO(totalCount,page,size);
        //计算offset
        Integer offset = (page-1)*size;
        List<Question> questions = questionMapper.selectByPage(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            Integer creator = question.getCreator();
            User user = userMapper.findByAccountId(String.valueOf(creator));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO findQuestionByUser(String accountId, Integer page, Integer size) {
        //查找属于该用户的所有问题
        Integer totalCount = questionMapper.totalCountByUser(Integer.parseInt(accountId));
        PaginationDTO paginationDTOByUser = new PaginationDTO(totalCount,page,size);
        //计算偏移量,分页查找
        Integer offset = (page-1)*size;
        List<Question> questions = questionMapper.selectByPageAndUser(Integer.parseInt(accountId), offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            Integer creator = question.getCreator();
            User user = userMapper.findByAccountId(String.valueOf(creator));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTOByUser.setQuestionDTOS(questionDTOS);
        return paginationDTOByUser;
    }

    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.findQuestionById(id);
        Integer creator = question.getCreator();
        User user = userMapper.findByAccountId(String.valueOf(creator));
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void updateOrCreate(Question question) {
        if(question.getId()==null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertQuestion(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
