package com.sheldon.sheldonblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.sheldon.sheldonblog.common.MyBaseService;
import com.sheldon.sheldonblog.dao.UserMapper;
import com.sheldon.sheldonblog.entity.User;
import com.sheldon.sheldonblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends MyBaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getList(int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);

        List<User> userList = userMapper.selectAll();

        return userList;
    }



}
