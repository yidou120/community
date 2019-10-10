package com.edou.community.mapper;

import com.edou.community.model.Question;
import org.apache.ibatis.annotations.*;
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

    @Select("select count(1) from question where creator = #{accountId}")
    Integer totalCountByUser(@Param("accountId") Integer accountId);

    @Select("select * from question where creator = #{accountId} limit #{offset},#{size}")
    List<Question> selectByPageAndUser(@Param("accountId") Integer accountId,@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select * from question where id = #{id}")
    Question findQuestionById(@Param("id") Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},creator=#{creator},tag=#{tag} where id=#{id}")
    void update(Question question);
}

