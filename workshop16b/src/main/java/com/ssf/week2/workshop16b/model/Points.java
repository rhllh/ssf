package com.ssf.week2.workshop16b.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Points {
    private int totalCount;
    @JsonProperty("color_1_points")
    private int color1Points;
    @JsonProperty("color_2_points")
    private int color2Points;

    public Points() {

    }

    public Points(int totalCount, int color1Points, int color2Points) {
        this.totalCount = totalCount;
        this.color1Points = color1Points;
        this.color2Points = color2Points;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getColor1Points() {
        return color1Points;
    }

    public void setColor1Points(int color1Points) {
        this.color1Points = color1Points;
    }

    public int getColor2Points() {
        return color2Points;
    }
    
    public void setColor2Points(int color2Points) {
        this.color2Points = color2Points;
    }

    
}
