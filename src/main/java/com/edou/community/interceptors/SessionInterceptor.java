package com.edou.community.interceptors;

import com.edou.community.mapper.UserMapper;
import com.edou.community.model.User;
import com.edou.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 中森明菜
 * @create 2019-09-28 10:53
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&!cookies.equals("")) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals("token")) {
                    String value = cookie.getValue();
                    UserExample example = new UserExample();
                    example.createCriteria()
                            .andTokenEqualTo(value);
                    List<User> users = userMapper.selectByExample(example);
//                    User user = userMapper.getUserByToken(value);
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                        break;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
