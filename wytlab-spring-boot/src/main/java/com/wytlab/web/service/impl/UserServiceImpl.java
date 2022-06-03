package com.wytlab.web.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wytlab.web.entity.UserEntity;
import com.wytlab.web.service.UserService;
import com.wytlab.web.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<UserEntity> page = this.page(
//                new Query<UserEntity>().getPage(params),
//                new QueryWrapper<UserEntity>()
//        );

//        return new PageUtils(page);
        return null;
    }

    @Override
    @SentinelResource(value = "getUser")  //aop
    public UserEntity getUser(int id){
        return null;
    }
    
    public UserEntity handleException(int id, BlockException ex) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("===被限流降级啦===");
        return userEntity;
    }
}