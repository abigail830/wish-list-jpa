package com.github.abigail830.wishlistjpa.controller;

import com.github.abigail830.wishlistjpa.domain.UserDTO;
import com.github.abigail830.wishlistjpa.domain.WishListBriefDTO;
import com.github.abigail830.wishlistjpa.entity.User;
import com.github.abigail830.wishlistjpa.service.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Collect full list of user",
            notes = "获取用户总列表",
            response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDTO> getAllUser() {
        return userService.findAllUser();
    }

    @ApiOperation(value = "Add user",
            notes = "添加新用户,如同样openId的用户已存在则会报错",
            response = UserDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) {

        logger.info("Add new user {}", userDTO);

        if (StringUtils.isNotBlank(userDTO.getOpenId())) {
            return userService.addUser(userDTO);
        }else{
            logger.warn("openId should not be empty for each user.");
            return userDTO;
        }

    }

    @ApiOperation(value = "Add or update user",
            notes = "更新用户信息，如用户不存在则直接添加",
            response = UserDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDTO addOrUpdateUser(@RequestBody UserDTO userDTO) {

        logger.info("Add or update user {}", userDTO);

        if (StringUtils.isNotBlank(userDTO.getOpenId())) {
            return userService.updateUser(userDTO);
        }else{
            logger.warn("openId should not be empty for each user.");
            return userDTO;
        }

    }

    @ApiOperation(value = "Get User by open_id",
            notes = "根据openID获取用户列表",
            response = UserDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/openId/{openId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO getUserByOpenId(
            @ApiParam(example = "oEmJ75YWmBSDgyz4KLi_yGL8MBV4") @RequestParam(value = "openId") String openId) {

        return userService.findUserByOpenId(openId);
    }

    @ApiOperation(value = "Add wish list for open_id",
            notes = "根据openID添加愿望清单",
            response = WishListBriefDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/openId/{openId}/wishList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<WishListBriefDTO> addWishListForUser(
            @ApiParam(example = "oEmJ75YWmBSDgyz4KLi_yGL8MBV4") @RequestParam(value = "openId") String openId,
            @RequestBody WishListBriefDTO wishList) {

            if(openId!=null)
                return userService.addWishListByOpenId(openId, wishList);
            else
                return null;
    }


}
