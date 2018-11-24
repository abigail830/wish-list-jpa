package com.github.abigail830.wishlistjpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.abigail830.wishlistjpa.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("UserDTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @ApiModelProperty(value = "小程序openId", example = "oEmJ75YWmBSDgyz4KLi_yGL8MBV4")
    private String openId;

    @ApiModelProperty(value = "性别", example = "F")
    private String gender;

    @ApiModelProperty(value = "昵称", example = "小孩子")
    private String nickName;

    @ApiModelProperty(value = "城市", example = "gz")
    private String city;

    @ApiModelProperty(value = "国家", example = "cn")
    private String country;

    @ApiModelProperty(value = "省份", example = "gd")
    private String province;

    @ApiModelProperty(value = "语言", example = "cn")
    private String lang;

    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

    public UserDTO() {
    }

    public UserDTO(String openId, String gender, String nickName, String city, String country, String province, String lang, String avatarUrl) {
        this.openId = openId;
        this.gender = gender;
        this.nickName = nickName;
        this.city = city;
        this.country = country;
        this.province = province;
        this.lang = lang;
        this.avatarUrl = avatarUrl;
    }

    public UserDTO(User user) {
        this.avatarUrl = user.getAvatarUrl();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.openId = user.getOpenId();
        this.gender = user.getGender();
        this.nickName = user.getNickName();
        this.province = user.getProvince();
        this.lang = user.getLang();
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                ", openId='" + openId + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", lang='" + lang + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
