package com.edou.community.community.controller;

import com.edou.community.community.dto.AccessTokenDTO;
import com.edou.community.community.dto.UserInfo;
import com.edou.community.community.httprequest.AccessToken;
import com.edou.community.community.mapper.UserMapper;
import com.edou.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author 中森明菜
 * @create 2019-09-22 9:31
 */
@Controller
public class AuthorizeController {
    @Autowired
    AccessToken accessToken;

    @Autowired
    UserMapper userMapper;

    @Value("${github.user.clientID}")
    private String clientID;

    @Value("${github.user.clientSecret}")
    private String clientSecret;

    @Value("${github.user.redirectURI}")
    private String redirectURI;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectURI);
        String responseToken = this.accessToken.getToken(accessTokenDTO);
        UserInfo userInfo = accessToken.getUser(responseToken);
        if(userInfo!=null){
            User user = new User();
            user.setAccountId(String.valueOf(userInfo.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setName(userInfo.getName());
            user.setBio(userInfo.getBio());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAvatarUrl(userInfo.getAvatar_url());
            userMapper.insertUser(user);
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            //用户不存在
            return "redirect:/";
        }
    }
}
