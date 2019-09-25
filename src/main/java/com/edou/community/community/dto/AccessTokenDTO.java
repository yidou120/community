package com.edou.community.community.dto;

import lombok.Data;

/**
 * @author 中森明菜
 * @create 2019-09-22 9:59
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
