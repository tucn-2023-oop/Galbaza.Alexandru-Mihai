// FarmerLeaderboardEntry.java
package com.example.farm2.Classes;

public class FarmerLeaderboardEntry {
    private String farmerName;
    private int livestockCount;
    private int poultryCount;
    private int equinesCount;
    private int miscCount;
    private int totalCount;
    private boolean hasCrown;

    public void setFarmerName(String farmerName) {
        this.farmerName=farmerName;
    }

    public void setLivestockCount(int livestockCount) {
        this.livestockCount=livestockCount;
    }

    public void setPoultryCount(int poultryCount) {
        this.poultryCount=poultryCount;
    }

    public void setEquinesCount(int equinesCount) {
        this.equinesCount=equinesCount;
    }

    public void setMiscCount(int miscCount) {
        this.miscCount=miscCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount=totalCount;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setHasCrown(boolean b) {
        this.hasCrown=b;
    }

    public boolean getCrown(){
        return hasCrown;
    }

    public <U> U getFarmerName() {
        return (U) farmerName;
    }

    public int getLivestockCount() {
        return livestockCount;
    }

    public int getPoultryCount() {
        return poultryCount;
    }

    public int getEquinesCount() {
        return equinesCount;
    }

    public int getMiscCount() {
        return miscCount;
    }


    // Constructors, getters, and setters...
}
