package com.wytlab.web.service;

import com.wytlab.web.entity.UserEntity;
import com.wytlab.web.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
public interface UserService {

    PageUtils queryPage(Map<String, Object> params);

    public UserEntity getUser(int id);
}

