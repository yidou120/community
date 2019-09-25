package com.edou.community.community.dto;

import com.edou.community.community.model.User;
import lombok.Data;

/**
 * @author 中森明菜
 * @create 2019-09-24 8:57
 */
@Data
public class QuestionDTO {
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
    private User user;
}
