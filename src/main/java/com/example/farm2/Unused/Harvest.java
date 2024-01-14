package com.example.farm2.Unused;

import java.time.LocalDate;

public class Harvest {
    private int harvestId;
    private int cropId;
    private int farmerId;
    private int quantityHarvested;
    private LocalDate dateHarvested;

    // Constructors (you can add more as needed)

    public Harvest() {
    }

    public Harvest(int cropId, int farmerId, int quantityHarvested, LocalDate dateHarvested) {
        this.cropId = cropId;
        this.farmerId = farmerId;
        this.quantityHarvested = quantityHarvested;
        this.dateHarvested = dateHarvested;
    }

    // Getters and Setters

    public int getHarvestId() {
        return harvestId;
    }

    public void setHarvestId(int harvestId) {
        this.harvestId = harvestId;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public int getQuantityHarvested() {
        return quantityHarvested;
    }

    public void setQuantityHarvested(int quantityHarvested) {
        this.quantityHarvested = quantityHarvested;
    }

    public LocalDate getDateHarvested() {
        return dateHarvested;
    }

    public void setDateHarvested(LocalDate dateHarvested) {
        this.dateHarvested = dateHarvested;
    }
}
