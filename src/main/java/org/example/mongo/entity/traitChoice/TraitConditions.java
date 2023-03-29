package org.example.mongo.entity.traitChoice;

import java.io.Serializable;

public class TraitConditions implements Serializable {

    private String logicalJoiner;
    private String traitSource;

    private String traitType;

    private String traitId;

    private String traitSymbol;

    private String traitValue;

    public String getLogicalJoiner() {
        return logicalJoiner;
    }

    public void setLogicalJoiner(String logicalJoiner) {
        this.logicalJoiner = logicalJoiner;
    }

    public String getTraitSource() {
        return traitSource;
    }

    public void setTraitSource(String traitSource) {
        this.traitSource = traitSource;
    }

    public String getTraitType() {
        return traitType;
    }

    public void setTraitType(String traitType) {
        this.traitType = traitType;
    }

    public String getTraitId() {
        return traitId;
    }

    public void setTraitId(String traitId) {
        this.traitId = traitId;
    }

    public String getTraitSymbol() {
        return traitSymbol;
    }

    public void setTraitSymbol(String traitSymbol) {
        this.traitSymbol = traitSymbol;
    }

    public String getTraitValue() {
        return traitValue;
    }

    public void setTraitValue(String traitValue) {
        this.traitValue = traitValue;
    }
}
