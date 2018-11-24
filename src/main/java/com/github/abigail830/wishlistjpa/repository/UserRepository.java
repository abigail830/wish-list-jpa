package com.github.abigail830.wishlistjpa.repository;

import com.github.abigail830.wishlistjpa.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByOpenId(String openId);

    void deleteByOpenId(String openId);

}
