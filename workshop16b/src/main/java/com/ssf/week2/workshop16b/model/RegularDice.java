package com.ssf.week2.workshop16b.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegularDice {
    private int diceCount;
    private int[] faces;

    public RegularDice() {

    }

    public RegularDice(int diceCount, int[] faces) {
        this.diceCount = diceCount;
        this.faces = faces;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int[] getFaces() {
        return faces;
    }

    public void setFaces(int[] faces) {
        this.faces = faces;
    }

}
