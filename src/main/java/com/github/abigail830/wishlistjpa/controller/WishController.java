package com.github.abigail830.wishlistjpa.controller;

import com.github.abigail830.wishlistjpa.domain.WishListBriefDTO;
import com.github.abigail830.wishlistjpa.entity.WishList;
import com.github.abigail830.wishlistjpa.service.WishService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/wish")
public class WishController {
    private static final Logger logger = LoggerFactory.getLogger(WishController.class);

    @Autowired
    private WishService wishService;

    @ApiOperation(value = "Collect full list of wish list",
            notes = "获取所有愿望清单概述",
            response = WishListBriefDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/list/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WishListBriefDTO> getAllWishListInBrief() {
        logger.debug("Going to collect all wish list in brief format.");
        return wishService.findAllBriefWishList();
    }

    @ApiOperation(value = "Collect wish list by creator user ID",
            notes = "根据创建人的user ID，获取对应愿望清单概述",
            response = WishListBriefDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/list/creator/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WishListBriefDTO> getAllWishListInBrief(
            @ApiParam(example = "1") @RequestParam(value = "id") String id) {
        logger.debug("Going to collect all wish list associated with user {}", id);

        return wishService.findAllBriefWishListByUserId(Integer.parseInt(id));
    }

}
