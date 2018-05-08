package com.sheldon.sheldonblog.controller;


import com.sheldon.sheldonblog.entity.User;
import com.sheldon.sheldonblog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/add",method = RequestMethod.GET)
    public void addOneUser(){
        logger.info("add a user once");
        User user = new User();
        user.setUsername("abc");
        user.setPassword("122222444");
        user.setNickname("aaaa");
        user.setStatus(0);
        user.setLevel(0);
        //SimpleDateFormat nowTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //nowTimeFormat.format(nowTime)
        Date nowTime = new Date();
        user.setGmtCreate(nowTime);
        user.setGmtModified(nowTime);
        userService.save(user);
    }

    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    public List<User> listUser(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize){
        logger.info("get user list");
        List<User> userList= userService.getList(pageNum,pageSize);
        return userList;
    }

}
