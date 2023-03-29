package org.example.mongo.entity.randomChoice;

import java.io.Serializable;

public class RandomChoiceConfig implements Serializable{

    private String randomType;

    private RandomBranches[] randomBranches;

    public String getRandomType() {
        return randomType;
    }

    public void setRandomType(String randomType) {
        this.randomType = randomType;
    }

    public RandomBranches[] getRandomBranches() {
        return randomBranches;
    }

    public void setRandomBranches(RandomBranches[] randomBranches) {
        this.randomBranches = randomBranches;
    }
}
