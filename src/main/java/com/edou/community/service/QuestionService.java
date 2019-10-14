package com.edou.community.service;

import com.edou.community.dto.PaginationDTO;
import com.edou.community.dto.QuestionDTO;
import com.edou.community.exception.CustomizeException;
import com.edou.community.exception.ErrorMessage;
import com.edou.community.mapper.QuestionMapper;
import com.edou.community.mapper.UserMapper;
import com.edou.community.model.Question;
import com.edou.community.model.QuestionExample;
import com.edou.community.model.User;
import com.edou.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
//        Integer totalCount = questionMapper.totalCount();
        PaginationDTO paginationDTO = new PaginationDTO(totalCount,page,size);
        //计算offset
        Integer offset = (page-1)*size;
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
//        List<Question> questions = questionMapper.selectByPage(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            Integer creator = question.getCreator();
            UserExample example = new UserExample();
            example.createCriteria()
                    .andAccountIdEqualTo(String.valueOf(creator));
            List<User> users = userMapper.selectByExample(example);
            User user = users.get(0);
//            User user = userMapper.findByAccountId(String.valueOf(creator));
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
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria()
                .andCreatorEqualTo(Integer.parseInt(accountId));
        Integer totalCount = (int)questionMapper.countByExample(example1);
//        Integer totalCount = questionMapper.totalCountByUser(Integer.parseInt(accountId));
        PaginationDTO paginationDTOByUser = new PaginationDTO(totalCount,page,size);
        //计算偏移量,分页查找
        Integer offset = (page-1)*size;
        QuestionExample example2 = new QuestionExample();
        example2.createCriteria()
                .andCreatorEqualTo(Integer.parseInt(accountId));
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example2, new RowBounds(offset, size));
//        List<Question> questions = questionMapper.selectByPageAndUser(Integer.parseInt(accountId), offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            Integer creator = question.getCreator();
            UserExample example = new UserExample();
            example.createCriteria()
                    .andAccountIdEqualTo(String.valueOf(creator));
            List<User> users = userMapper.selectByExample(example);
            User user = users.get(0);
//            User user = userMapper.findByAccountId(String.valueOf(creator));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTOByUser.setQuestionDTOS(questionDTOS);
        return paginationDTOByUser;
    }

    public QuestionDTO findQuestionById(Integer id) {

        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(ErrorMessage.QUESTION_NOT_FIND);
        }
//        Question question = questionMapper.findQuestionById(id);
        Integer creator = question.getCreator();
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(String.valueOf(creator));
        List<User> users = userMapper.selectByExample(example);
        User user = users.get(0);
//        User user = userMapper.findByAccountId(String.valueOf(creator));
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void updateOrCreate(Question question) {
        if(question.getId()==null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
//            questionMapper.insertQuestion(question);
        }else {
            Question record = new Question();
            record.setGmtModified(System.currentTimeMillis());
            record.setTitle(question.getTitle());
            record.setTag(question.getTag());
            record.setDescription(question.getDescription());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int i = questionMapper.updateByExampleSelective(record, example);
            if(i!=1){
                throw new CustomizeException(ErrorMessage.QUESTION_NOT_FIND);
            }
//            questionMapper.update(question);
        }
    }
}
