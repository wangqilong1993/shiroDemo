package com.wql.service;

import com.wql.model.User;

/**
 * Created by PCX on 2018/12/4.
 */
public interface UserService {
    User findByName(String name);
}
