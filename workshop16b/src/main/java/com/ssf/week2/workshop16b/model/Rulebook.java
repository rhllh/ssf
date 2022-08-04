package com.ssf.week2.workshop16b.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Rulebook {
    private int totalCount;
    private String file;

    public Rulebook() {

    }

    public Rulebook(int totalCount, String file) {
        this.totalCount = totalCount;
        this.file = file;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
