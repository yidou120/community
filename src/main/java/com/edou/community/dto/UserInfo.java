package com.edou.community.dto;

import lombok.Data;

/**
 * @author 中森明菜
 * @create 2019-09-22 10:21
 */
@Data
public class UserInfo {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
