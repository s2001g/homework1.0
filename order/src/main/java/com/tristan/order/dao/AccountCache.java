package com.tristan.order.dao;

import com.tristan.order.po.Account;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class AccountCache {
    @Autowired
    private RedisTemplate redisTemplate;

    //    判断key是否存在
    public boolean hasKey(int key){
        return redisTemplate.hasKey(key);
    }

    public Account add(Account account){

        redisTemplate.boundValueOps(account.getId()).set(account);
        return account;
    }

    public String get(int accountId){
        Account account;
        String accountJSON =  redisTemplate.opsForValue().get(accountId).toString();
        return accountJSON;
    }
}
