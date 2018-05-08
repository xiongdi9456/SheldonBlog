package com.sheldon.sheldonblog.common;

/*
* 通用Service接口
* */

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyServiceInterface<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);

}
