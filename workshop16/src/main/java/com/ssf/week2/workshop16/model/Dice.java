package com.ssf.week2.workshop16.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Dice {
    private int totalCount;
    private RegularDice regularDice;
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


    
}
