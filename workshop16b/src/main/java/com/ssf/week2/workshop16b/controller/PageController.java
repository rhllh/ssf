package com.ssf.week2.workshop16b.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.week2.workshop16b.model.Backgammon;
import com.ssf.week2.workshop16b.model.Board;
import com.ssf.week2.workshop16b.model.Counters;
import com.ssf.week2.workshop16b.model.Dice;
import com.ssf.week2.workshop16b.model.DiceShaker;
import com.ssf.week2.workshop16b.model.DoublingCubeDice;
import com.ssf.week2.workshop16b.model.JsonInput;
import com.ssf.week2.workshop16b.model.Pieces;
import com.ssf.week2.workshop16b.model.Points;
import com.ssf.week2.workshop16b.model.RegularDice;
import com.ssf.week2.workshop16b.model.Rulebook;
import com.ssf.week2.workshop16b.service.GameRedis;

import jakarta.json.JsonObject;

@Controller
@RequestMapping(path="/")
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    GameRedis service;
    
    @GetMapping
    public String showGameList(Model model) {
        List<Backgammon> allGames = service.getAllGames();
        model.addAttribute("gameList", allGames);

        return "index";
    }

    @GetMapping(path="/add")
    public String getAddForm(Model model) {
        model.addAttribute("backgammon", new Backgammon());
        model.addAttribute("jsoninput", new JsonInput());

        return "addForm";
    }

    @PostMapping(path="/added")
    public String addGame(@ModelAttribute("jsoninput") JsonInput ji, 
                          @ModelAttribute("backgammon") Backgammon bg, Model model) {
        JsonObject jo = service.unmarshallJson(ji);
        String name = jo.getString("name");
        int playerCount = jo.getInt("player_count");
        bg.setName(name);
        bg.setPlayerCount(playerCount);
        
        //logger.info("stringJsonInput >> " + ji.getJsonInputString());

        Rulebook rulebook = new Rulebook(jo.getJsonObject("pieces").getJsonObject("rulebook").getInt("total_count"), 
                                            jo.getJsonObject("pieces").getJsonObject("rulebook").getString("file"));
        Points points = new Points(jo.getJsonObject("pieces").getJsonObject("board").getJsonObject("points").getInt("total_count"),
                                    jo.getJsonObject("pieces").getJsonObject("board").getJsonObject("points").getInt("color_1_points"),
                                    jo.getJsonObject("pieces").getJsonObject("board").getJsonObject("points").getInt("color_2_points"));
        Board board = new Board(jo.getJsonObject("pieces").getJsonObject("board").getInt("total_count"), points);
        Counters counters = new Counters(jo.getJsonObject("pieces").getJsonObject("counters").getInt("total_count"), 
                                            jo.getJsonObject("pieces").getJsonObject("counters").getInt("color_1_counters"),
                                            jo.getJsonObject("pieces").getJsonObject("counters").getInt("color_2_counters"));
        RegularDice regularDice = new RegularDice(jo.getJsonObject("pieces").getJsonObject("dice").getJsonObject("regular_dice").getInt("dice_count"),
                                                    Arrays.stream(jo.getJsonObject("pieces").getJsonObject("dice").getJsonObject("regular_dice")
                                                    .get("faces").toString().replace("[","").replace("]","")
                                                    .split(",")).mapToInt(Integer::parseInt).toArray());
        DoublingCubeDice doublingCubeDice = new DoublingCubeDice(jo.getJsonObject("pieces").getJsonObject("dice").getJsonObject("doubling_cube_dice").getInt("dice_count"),
                                                                    Arrays.stream(jo.getJsonObject("pieces").getJsonObject("dice").getJsonObject("doubling_cube_dice")
                                                                    .get("faces").toString().replace("[","").replace("]","")
                                                                    .split(",")).mapToInt(Integer::parseInt).toArray());
        Dice dice = new Dice(jo.getJsonObject("pieces").getJsonObject("dice").getInt("total_count"),
                                regularDice, doublingCubeDice);                                                            
        DiceShaker diceShaker = new DiceShaker(jo.getJsonObject("pieces").getJsonObject("dice_shaker").getInt("total_count"));
        Pieces pieces = new Pieces(rulebook, board, counters, dice, diceShaker);
        
        bg.setPieces(pieces);
        service.save(bg);

        return "added";
    }

    @GetMapping("/delete")
    public String openDeleteForm(Model model) {
        List<Backgammon> allGames = service.getAllGames();
        model.addAttribute("gameList", allGames);

        Backgammon bg = new Backgammon();
        bg.setId("");
        model.addAttribute("backgammon", bg);

        return "deleteForm";
    }

    @PostMapping("/deleted")
    public String deleteGame(@ModelAttribute("backgammon") Backgammon bg, Model model) {

        String id = bg.getId();
        logger.info("id to delete >> " + id);

        int deleted = service.deleteById(id);
        logger.info("id deleted? >> " + deleted);

        List<Backgammon> allGames = service.getAllGames();
        model.addAttribute("gameList", allGames);

        return "index";
    }

}
