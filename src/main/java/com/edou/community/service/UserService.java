package com.edou.community.service;

import com.edou.community.mapper.UserMapper;
import com.edou.community.model.User;
import com.edou.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 中森明菜
 * @create 2019-10-01 19:09
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void updateOrCreate(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
//        User byAccountId = userMapper.findByAccountId(user.getAccountId());
        if(users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //插入
            userMapper.insert(user);
//            userMapper.insertUser(user);
        }else {
            //更新
            User userFlag = users.get(0);
            User userZero = new User();
            userZero.setGmtModified(System.currentTimeMillis());
            userZero.setName(user.getName());
            userZero.setBio(user.getBio());
            userZero.setToken(user.getToken());
            userZero.setAvatarUrl(user.getAvatarUrl());
            UserExample example1 = new UserExample();
            example1.createCriteria()
                    .andIdEqualTo(userFlag.getId());
            userMapper.updateByExampleSelective(userZero, example1);
//            userMapper.update(byAccountId);
        }
    }
}
