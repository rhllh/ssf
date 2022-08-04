package com.ssf.week2.workshop14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ssf.week2.workshop14.model.Contact;

@Component
public class ContactsRedis implements ContactsRepo {

    @Autowired
    RedisTemplate<String, Object> template;

    @Override
    public void save(Contact ctc) {
        // .opsForValue() gets value operations
        // .set() takes in <K,V> pair
        // key = contactId, value = entire contact object
        template.opsForValue().set(ctc.getId(), ctc);
        
    }

    @Override
    public Contact findById(String contactId) {
        // to retrieve, retrieve by key (id)
        Contact result = (Contact) template.opsForValue().get(contactId);
        return result;
    }
    
}
