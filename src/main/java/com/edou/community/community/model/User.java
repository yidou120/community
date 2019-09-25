package com.edou.community.community.model;

import lombok.Data;

/**
 * @author 中森明菜
 * @create 2019-09-22 15:26
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String token;
    private String accountId;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
