package com.edou.community.community.mapper;

import com.edou.community.community.model.User;
import org.apache.ibatis.annotations.*;
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

    @Select("select * from user where account_id=#{accountID}")
    User findByAccountId(@Param("accountID") String accountID);

    @Update("update user set name = #{name},token = #{token},gmt_modified=#{gmtModified},bio=#{bio},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User byAccountId);
}
