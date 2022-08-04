package com.ssf.week2.workshop16.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssf.week2.workshop16.model.Backgammon;
import com.ssf.week2.workshop16.service.GameRedis;

@RestController
@RequestMapping(path="/api", produces="application/json")
public class GameController {

    @Autowired
    GameRedis service;
    
    @PostMapping(path="/boardgame", consumes="application/json")
    public ResponseEntity<Backgammon> postGame(@RequestBody Backgammon bg) {
        try {
            URI location = new URI("/api/boardgame");

            int x = service.save(bg);

            if (x > 0)
                bg.setInsertCount(x);

            return ResponseEntity.created(location).body(bg);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path="/boardgame/{id}", produces="application/json")
    public ResponseEntity<Backgammon> getGame(@PathVariable String id) {
        try {
            final Backgammon bg = service.findById(id);

            return ResponseEntity.ok(bg);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path="/boardgame/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<Backgammon> putGame(@PathVariable String id,
                                              @RequestBody Backgammon bg) {
        try {
            int x = service.update(bg);

            if (x > 0)
                bg.setUpdateCount(x);

            return ResponseEntity.ok(bg);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
