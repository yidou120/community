package com.edou.community.httprequest;

import com.alibaba.fastjson.JSON;
import com.edou.community.dto.AccessTokenDTO;
import com.edou.community.dto.UserInfo;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 中森明菜
 * @create 2019-09-22 9:36
 */
@Component
public class AccessToken {
    public String getToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String[] split1 = split[0].split("=");
            String token = split1[1];
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public UserInfo getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return JSON.parseObject(string,UserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
