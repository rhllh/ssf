package com.ssf.week2.workshop17.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssf.week2.workshop17.model.Excuse;

@Service
public class ExcuseService {
    private static final Logger logger = LoggerFactory.getLogger(ExcuseService.class);
    
    private static final String URL = "https://excuser.herokuapp.com/v1/excuse";

    @Autowired
    RestTemplate template = new RestTemplate();

    public List<String> generateExcuse(String category, Integer count) {
        String excuseUrl = UriComponentsBuilder.fromUriString(URL)
                            .path("/" + category)
                            .path("/" + String.valueOf(count))
                            .toUriString();
        logger.info("url >> " + excuseUrl);

        ResponseEntity<String> resp = null;
        List<String> excuseList = new ArrayList<>();
        try {
            resp = template.getForEntity(excuseUrl, String.class);
            excuseList = Excuse.getLineFromBody(resp.getBody());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return excuseList;
    }
}
