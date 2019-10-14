package com.edou.community.exception;

/**
 * @author 中森明菜
 * @create 2019-10-13 20:13
 */
public class CustomizeException extends RuntimeException {
    private String message;
    public String getMessage(){
        return message;
    }
    public CustomizeException(ErrorMessage errorMessage){
        this.message = errorMessage.getMessage();
    }
}
