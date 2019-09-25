package com.edou.community.community.mapper;

import com.edou.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 中森明菜
 * @create 2019-09-22 15:25
 */
@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insertUser(User user);
    @Select("select * from user where token=#{value}")
    User getUserByToken(@Param("value") String value);
    @Select("select * from user where id=#{id}")
    User findByID(@Param("id") Integer id);
}
