package com.github.abigail830.wishlistjpa.service;

import com.github.abigail830.wishlistjpa.domain.UserDTO;
import com.github.abigail830.wishlistjpa.domain.WishListBriefDTO;
import com.github.abigail830.wishlistjpa.entity.WishList;
import com.github.abigail830.wishlistjpa.repository.WishListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WishService {

    private static final Logger log = LoggerFactory.getLogger(WishService.class);

    @Autowired
    private WishListRepository wishListRepository;


    public List<WishListBriefDTO> findAllBriefWishList(){
        log.debug("Going to get all wish list from DB");
        List<WishList> wishLists = (List<WishList>) wishListRepository.findAll();
        return wishLists.stream().map(WishListBriefDTO::new).collect(Collectors.toList());
    }

    public List<WishListBriefDTO> findAllBriefWishListByUserId(Integer userId){
        log.debug("Going to get all wish list from DB");
        List<WishList> wishLists = wishListRepository.findByUserId(userId);
        return wishLists.stream().map(WishListBriefDTO::new).collect(Collectors.toList());
    }



}
