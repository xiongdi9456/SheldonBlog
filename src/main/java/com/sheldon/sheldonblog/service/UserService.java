package com.sheldon.sheldonblog.service;

import com.sheldon.sheldonblog.common.MyServiceInterface;
import com.sheldon.sheldonblog.entity.User;

import java.util.List;

public interface UserService extends MyServiceInterface<User>{

    public List<User> getList(int pageNum, int pageSize);

}
