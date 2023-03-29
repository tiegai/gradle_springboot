package org.example.mongo.entity.traitChoice;

import java.io.Serializable;

public class TraitChoiceConfig implements Serializable {

    private String[] traitIds;

    private TraitBranches[] traitBranches;

    public String[] getTraitIds() {
        return traitIds;
    }

    public void setTraitIds(String[] traitIds) {
        this.traitIds = traitIds;
    }

    public TraitBranches[] getTraitBranches() {
        return traitBranches;
    }

    public void setTraitBranches(TraitBranches[] traitBranches) {
        this.traitBranches = traitBranches;
    }
}
