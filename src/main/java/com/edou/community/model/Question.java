package com.edou.community.model;

import lombok.Data;

/**
 * @author 中森明菜
 * @create 2019-09-22 21:25
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private String tag;
}
