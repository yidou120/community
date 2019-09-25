package com.edou.community.community.mapper;

import com.edou.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 中森明菜
 * @create 2019-09-22 21:27
 */
@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insertQuestion(Question question);
    @Select("select * from question")
    List<Question> selectAll();
    @Select("select count(1) from question")
    Integer totalCount();
    @Select("select * from question limit #{offset},#{size}")
    List<Question> selectByPage(Integer offset,Integer size);
}
