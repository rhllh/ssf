package com.ssf.week2.workshop16b.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pieces {
    private Rulebook rulebook;
    private Board board;
    private Counters counters;
    private Dice dice;
    private DiceShaker diceShaker;

    public Pieces() {

    }

    public Pieces(Rulebook rulebook, Board board, Counters counters, Dice dice, DiceShaker diceShaker) {
        this.rulebook = rulebook;
        this.board = board;
        this.counters = counters;
        this.dice = dice;
        this.diceShaker = diceShaker;
    }

    public Rulebook getRulebook() {
        return rulebook;
    }

    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Counters getCounters() {
        return counters;
    }

    public void setCounters(Counters counters) {
        this.counters = counters;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public DiceShaker getDiceShaker() {
        return diceShaker;
    }
    
    public void setDice_Shaker(DiceShaker diceShaker) {
        this.diceShaker = diceShaker;
    }

    
}
