package com.edou.community.community.service;

import com.edou.community.community.mapper.UserMapper;
import com.edou.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 中森明菜
 * @create 2019-10-01 19:09
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void updateOrCreate(User user) {
        User byAccountId = userMapper.findByAccountId(user.getAccountId());
        if(byAccountId==null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //插入
            userMapper.insertUser(user);
        }else {
            //更新
            byAccountId.setGmtModified(System.currentTimeMillis());
            byAccountId.setName(user.getName());
            byAccountId.setBio(user.getBio());
            byAccountId.setToken(user.getToken());
            byAccountId.setAvatarUrl(user.getAvatarUrl());
            userMapper.update(byAccountId);
        }
    }
}
