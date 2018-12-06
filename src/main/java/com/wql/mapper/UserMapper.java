package com.wql.mapper;

import com.wql.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by PCX on 2018/12/4.
 */
@Repository
public interface UserMapper {

    User findByName(@Param("name")String name);
}
