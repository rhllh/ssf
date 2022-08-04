package com.ssf.week2.workshop16b.model;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Dice {
    private int totalCount;
    private RegularDice regularDice;
    private DoublingCubeDice doublingCubeDice;

    public Dice() {

    }

    public Dice(int totalCount, RegularDice regularDice, DoublingCubeDice doublingCubeDice) {
        this.totalCount = totalCount;
        this.regularDice = regularDice;
        this.doublingCubeDice = doublingCubeDice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public RegularDice getRegularDice() {
        return regularDice;
    }

    public void setRegularDice(RegularDice regularDice) {
        this.regularDice = regularDice;
    }

    public DoublingCubeDice getDoublingCubeDice() {
        return doublingCubeDice;
    }

    public void setDoublingCubeDice(DoublingCubeDice doublingCubeDice) {
        this.doublingCubeDice = doublingCubeDice;
    }
    
}
