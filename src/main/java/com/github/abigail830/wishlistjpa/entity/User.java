package com.github.abigail830.wishlistjpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String openId;

    @Size(max = 45)
    private String gender;

    private String nickName;
    private String city;
    private String country;
    private String province;
    private String lang;
    private String avatarUrl;

    @OneToMany(mappedBy="creator",cascade=CascadeType.ALL)
    private List<WishList> wishLists = new ArrayList<>();

    public User() {
    }

    public User(@NotBlank String openId) {
        this.openId = openId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public void addWishList(WishList wishList){
        this.wishLists.add(wishList);
        wishList.setCreator(this);
    }

    public void removeWishList(WishList wishList){
        wishLists.remove(wishList);
        wishList.setCreator(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", lang='" + lang + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", wishLists=" + wishLists +
                '}';
    }
}
