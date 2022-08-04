package com.ssf.week2.workshop16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssf.week2.workshop16.model.Backgammon;

@Service
public class GameRedis implements GameRepo {

    @Autowired
    @Qualifier("games")
    RedisTemplate<String, Backgammon> redisTemplate;

    @Override
    public int save(Backgammon bg) {
        redisTemplate.opsForValue().set(bg.getId(), bg);
        Backgammon result = redisTemplate.opsForValue().get(bg.getId());
        
        if (result != null) {
            return 1;
        }

        return 0;
    }

    @Override
    public Backgammon findById(String id) {
        Backgammon result = redisTemplate.opsForValue().get(id);
        
        return result;
    }

    @Override
    public int update(Backgammon bg) {
        if (bg.isUpsert()) {
            redisTemplate.opsForValue().setIfAbsent(bg.getId(), bg);
        } else {
            redisTemplate.opsForValue().setIfPresent(bg.getId(), bg);
        }

        // in redis, if allowed, return 1 else 0
        Backgammon result = redisTemplate.opsForValue().get(bg.getId());
        if (result != null) {
            return 1;
        }
        return 0;
    }
    
}
