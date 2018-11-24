package com.github.abigail830.wishlistjpa.service;

import com.github.abigail830.wishlistjpa.domain.UserDTO;
import com.github.abigail830.wishlistjpa.domain.WishListBriefDTO;
import com.github.abigail830.wishlistjpa.entity.User;
import com.github.abigail830.wishlistjpa.entity.WishList;
import com.github.abigail830.wishlistjpa.repository.UserRepository;
import com.github.abigail830.wishlistjpa.repository.WishListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishListRepository wishListRepository;

    private static final ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    public UserDTO addUser(UserDTO userDTO){
        log.debug("Going to add user to DB: {}", userDTO);
        userRepository.save(convertUserDTOToUser(userDTO));
        return findUserByOpenId(userDTO.getOpenId());
    }

    public UserDTO updateUser(UserDTO userDTO){
        log.debug("Going to update user info for {}", userDTO);

        return userRepository.findByOpenId(userDTO.getOpenId())
                .stream().findFirst().map(user -> {
                    User udpatedUser = convertUserDTOToUser(userDTO);
                    udpatedUser.setId(user.getId());
                    userRepository.save(udpatedUser);
                    return findUserByOpenId(user.getOpenId());
                }).orElse(addUser(userDTO));
    }

    public UserDTO findUserByOpenId(String openId){
        log.debug("Going to query user by openId: {}", openId);
        return new UserDTO(userRepository.findByOpenId(openId).stream().findFirst().orElse(new User()));
    }

    public List<UserDTO> findAllUser(){
        log.debug("Going to get all user from DB");
        List<User> userList = (List<User>) userRepository.findAll();
        return userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public void removeAllUser(){
        log.warn("Going to clean up all user data in DB");
        userRepository.deleteAll();
    }

    public void removeUserByOpenId(String openId){
        log.warn("Going to clean up user with openId {} in DB", openId);
        userRepository.deleteByOpenId(openId);
    }

    public List<WishListBriefDTO> addWishListByOpenId(String openId, WishListBriefDTO wishListBriefDTO) {

        Optional<User> optionalUser = userRepository.findByOpenId(openId).stream().findFirst();
        if(optionalUser.isPresent()) {
            log.info("User found with openId {} during add WishList.", openId);
            User userFound = optionalUser.get();
            try {
                WishList wishList = convertWishListBriefDTOToWishList(wishListBriefDTO);
                wishList.setCreator(userFound);
                wishList.setUserId(userFound.getId());
                wishListRepository.save(wishList);

                userFound.addWishList(wishList);
                userRepository.save(userFound);

                log.info("WishList {} saved for user {}", wishList.getDescription(), openId);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return wishListRepository.findByUserId(userFound.getId())
                    .stream().map(WishListBriefDTO::new).collect(Collectors.toList());
        }
        log.warn("User not found with openId {} during add WishList.", openId);
        return new ArrayList<>();

    }

    private User convertUserDTOToUser(UserDTO userDTO){
        User user = new User();
        user.setOpenId(userDTO.getOpenId());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setGender(userDTO.getGender());
        user.setLang(userDTO.getLang());
        user.setNickName(userDTO.getNickName());
        user.setProvince(userDTO.getProvince());
        return user;
    }

    private WishList convertWishListBriefDTOToWishList(WishListBriefDTO wishListBriefDTO) throws ParseException {
        WishList wishList = new WishList();
        wishList.setDescription(wishListBriefDTO.getListDescription());
        wishList.setDueTime(new java.sql.Timestamp(dateFormatter.get().parse(wishListBriefDTO.getListDueTime()).getTime()));

        return wishList;

    }


}
