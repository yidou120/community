package com.edou.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 中森明菜
 * @create 2019-09-25 8:55
 * @param
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private Integer currentPage;
    private Boolean hasFirstPage;
    private Boolean hasEndPage;
    private Boolean hasPrevious;
    private Boolean hasLast;
    private Integer totalPage;
    private Integer totalCount;
    private Integer begin;
    private Integer end;
    private List<Integer> pages;
    public PaginationDTO(Integer totalCount,Integer page,Integer size){
        //计算总页数
        this.currentPage = page;
        this.totalCount = totalCount;
        hasFirstPage = false;
        hasEndPage = false;
        hasLast = true;
        hasPrevious = true;
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = (totalCount/size)+1;
        }
        //判断页码是否超出或者非法负数
        if(page<1){
            this.currentPage = 1;
        }
        if(page>totalPage){
            this.currentPage = totalPage;
        }
        //计算begin和end
        if(currentPage-3<=1){
            begin = 1;
        }else {
            begin = currentPage-3;
            hasFirstPage = true;
        }
        if(currentPage+3<totalPage){
            end = currentPage+3;
            hasEndPage = true;
        }else {
            end = totalPage;
        }
        pages = new ArrayList<>();
        //填充pages
        for(int i = begin;i<=end;i++){
            pages.add(i);
        }
        //是否显示跳转上页和下页
        if(currentPage==1||totalCount==0){
            hasPrevious = false;
        }
        if(currentPage==totalPage){
            hasLast = false;
        }
    }
}
