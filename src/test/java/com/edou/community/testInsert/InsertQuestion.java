package com.edou.community.testInsert;

import com.edou.community.CommunityApplication;
import com.edou.community.mapper.QuestionMapper;
import com.edou.community.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 中森明菜
 * @create 2019-09-25 9:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommunityApplication.class})
public class InsertQuestion {
    @Autowired
    QuestionMapper questionMapper;
    @Test
    public void testInsert(){
        for(int i = 1;i<51;i++) {
            Question question = new Question();
            question.setTitle("title"+i);
            question.setDescription("test"+i);
            question.setCreator(22814269);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setTag("标签1,标签2");
            questionMapper.insert(question);
//            questionMapper.insertQuestion(question);
            System.out.println("插入成功"+i);
        }
    }
}
