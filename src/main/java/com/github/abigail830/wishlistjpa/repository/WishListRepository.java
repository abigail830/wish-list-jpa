package com.github.abigail830.wishlistjpa.repository;

import com.github.abigail830.wishlistjpa.entity.WishList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WishListRepository extends CrudRepository<WishList, Integer> {

    List<WishList> findByUserId(Integer userId);

}
