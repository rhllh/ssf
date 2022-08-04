package com.ssf.week1.workshop12.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Generate {
    private int numberCount;
    private List<Integer> listOfGeneratedNumbers = new ArrayList<>();

    public Generate() {

    }

    public Generate(int numberCount) {
        this.numberCount = numberCount;
        this.listOfGeneratedNumbers.addAll(generateListOfRandomNumbers(this.numberCount));
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }

    public List<Integer> getListOfGeneratedNumbers() {
        return listOfGeneratedNumbers;
    }
    
    public void setListOfGeneratedNumbers(List<Integer> listOfGeneratedNumbers) {
        this.listOfGeneratedNumbers = listOfGeneratedNumbers;
    }

    private Set<Integer> generateListOfRandomNumbers(int numberCount) {
        Random r = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 0; i < numberCount; i++) {
            int n = r.nextInt(31);
            while (uniqueNumbers.contains(n)) {
                n = r.nextInt(numberCount);
            }
            uniqueNumbers.add(n);
        }

        return uniqueNumbers;
    }

}
