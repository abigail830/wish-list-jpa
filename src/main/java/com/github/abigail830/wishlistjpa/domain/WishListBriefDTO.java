package com.github.abigail830.wishlistjpa.domain;

import com.github.abigail830.wishlistjpa.entity.WishList;
import io.swagger.annotations.ApiModelProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WishListBriefDTO {

    @ApiModelProperty(value = "用户UserID")
    private Integer creatorUserId;

    @ApiModelProperty(value = "愿望清单概述",  example = "新年愿望清单")
    private String listDescription;

    @ApiModelProperty(value = "愿望清单创建日期",  example = "2018-09-01")
    private String listCreateTime;

    @ApiModelProperty(value = "愿望清单目标兑现时间",  example = "2018-10-10")
    private String listDueTime;


    private static final ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public WishListBriefDTO() {

    }

    public WishListBriefDTO(WishList wishList){
        this.listCreateTime = dateFormatter.get().format(wishList.getCreateTime().getTime());
        this.listDueTime = dateFormatter.get().format(wishList.getDueTime().getTime());
        this.listDescription = wishList.getDescription();
        this.creatorUserId = wishList.getUserId();
    }


    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }

    public String getListCreateTime() {
        return listCreateTime;
    }

    public String getListDueTime() {
        return listDueTime;
    }

    public void setListDueTime(String listDueTime) {
        this.listDueTime = listDueTime;
    }


    @Override
    public String toString() {
        return "WishListBriefDTO{" +
                "creatorUserId='" + creatorUserId + '\'' +
                ", listDescription='" + listDescription + '\'' +
                ", listCreateTime='" + listCreateTime + '\'' +
                ", listDueTime='" + listDueTime + '\'' +
                '}';
    }
}
