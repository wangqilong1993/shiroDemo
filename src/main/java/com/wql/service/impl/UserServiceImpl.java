package com.wql.service.impl;

import com.wql.mapper.UserMapper;
import com.wql.model.User;
import com.wql.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PCX on 2018/12/4.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSession sqlSession;


    @Override
    public User findByName(String name) {
        return sqlSession.getMapper(UserMapper.class).findByName(name);
    }
}
