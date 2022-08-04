package com.ssf.week2.workshop16b.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Counters {
    private int totalCount;
    @JsonProperty("color_1_counters")
    private int color1Counters;
    @JsonProperty("color_2_counters")
    private int color2Counters;

    public Counters() {

    }

    public Counters(int totalCount, int color1Counters, int color2Counters) {
        this.totalCount = totalCount;
        this.color1Counters = color1Counters;
        this.color2Counters = color2Counters;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getColor1Counters() {
        return color1Counters;
    }

    public void setColor1Counters(int color1Counters) {
        this.color1Counters = color1Counters;
    }

    public int getColor2Counters() {
        return color2Counters;
    }

    public void setColor2Counters(int color2Counters) {
        this.color2Counters = color2Counters;
    }

}
