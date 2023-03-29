package org.example.mongo.entity.randomChoice;

import java.io.Serializable;

public class RandomBranches implements Serializable{

    private boolean otherBranch;

    private int numberOfAudience;

    private int weight;

    public boolean isOtherBranch() {
        return otherBranch;
    }

    public void setOtherBranch(boolean otherBranch) {
        this.otherBranch = otherBranch;
    }

    public int getNumberOfAudience() {
        return numberOfAudience;
    }

    public void setNumberOfAudience(int numberOfAudience) {
        this.numberOfAudience = numberOfAudience;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
