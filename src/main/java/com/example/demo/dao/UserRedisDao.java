package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author by HZL
 * @date 2019/12/19 20:49
 * @description
 */

@Repository
public class UserRedisDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name="stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Resource(name="redisTemplate")
    ValueOperations<Object,Object>valOps;


    public void stringRedisTemplateDemo() {
        valOpsStr.set("user1", "hzl");
        valOpsStr.set("password1", "123456");
        valOpsStr.set("user2", "admin");
        valOpsStr.set("password2", "123456");
    }

    public String getStringByRedis() {
        return valOpsStr.get("user1");
    }

    //新增
    public void save() {
        valOpsStr.set("user3", "zy");
        valOpsStr.set("password1", "123456");
        valOpsStr.set("user4", "yz");
        valOpsStr.set("password2", "123456");
    }

}

