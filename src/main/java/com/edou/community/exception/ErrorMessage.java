package com.edou.community.exception;

/**
 * @author 中森明菜
 * @create 2019-10-13 20:13
 */
public enum ErrorMessage {
    QUESTION_NOT_FIND("你访问的问题不存在了");
    private String message;
    ErrorMessage(String message){
       this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
