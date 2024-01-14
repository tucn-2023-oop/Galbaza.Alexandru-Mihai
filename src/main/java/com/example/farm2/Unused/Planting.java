package com.example.farm2.Unused;

import java.time.LocalDate;

public class Planting {
    private int plantingId;
    private int cropId;
    private int farmerId;
    private int acreage;
    private LocalDate datePlanted;

    public int getPlantingId() {
        return plantingId;
    }

    public void setPlantingId(int plantingId) {
        this.plantingId = plantingId;
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

    public int getAcreage() {
        return acreage;
    }

    public void setAcreage(int acreage) {
        this.acreage = acreage;
    }

    public LocalDate getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(LocalDate datePlanted) {
        this.datePlanted = datePlanted;
    }
}
