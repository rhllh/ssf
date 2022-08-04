package com.ssf.week2.workshop16b.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssf.week2.workshop16b.model.Backgammon;
import com.ssf.week2.workshop16b.model.JsonInput;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GameRedis implements GameRepo {
    private static final Logger logger = LoggerFactory.getLogger(GameRedis.class);

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

    public List<Backgammon> getAllGames() {
        Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());
        Iterator<byte[]> it = keys.iterator();
        String key;
        Backgammon result;
        List<Backgammon> backgammonList = new ArrayList<>();

        while (it.hasNext()) {
            byte[] data = (byte[]) it.next();
            key = new String(data, 0, data.length);
            logger.info("key >> " + key);
            result = findById(key);
            logger.info("game name >> " + result.getName());
            backgammonList.add(result);
        }

        return backgammonList;
    }

    public JsonObject unmarshallJson(JsonInput ji) {
        String jsonString = ji.getJsonInputString();
        JsonObject o = null;
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            JsonReader r = Json.createReader(is);
            o = r.readObject();
        } catch (Exception e) { 
            logger.error(e.getMessage());
        }
        return o;
    }
    
    public int deleteById(String id) {
        boolean result = redisTemplate.delete(id);

        if (result == true) {
            return 1;
        }
        return 0;
        
    }
}
