package org.example.mongo.entity.traitChoice;

import java.io.Serializable;

public class TraitBranches implements Serializable {

    private boolean otherBranch;

    private TraitConditions[] traitConditions;

    public boolean isOtherBranch() {
        return otherBranch;
    }

    public void setOtherBranch(boolean otherBranch) {
        this.otherBranch = otherBranch;
    }

    public TraitConditions[] getTraitConditions() {
        return traitConditions;
    }

    public void setTraitConditions(TraitConditions[] traitConditions) {
        this.traitConditions = traitConditions;
    }
}
