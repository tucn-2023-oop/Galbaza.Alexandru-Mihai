package com.example.farm2.Classes;

import java.time.LocalDate;

public class Livestock {
    private int livestockId;
    private String livestockName;
    private String type;
    private int quantity;
    private int farmerId;

    // Constructors

    public Livestock() {
    }

    public Livestock(String livestockName, String type, int quantity, int farmerId) {
        this.livestockName = livestockName;
        this.type = type;
        this.quantity = quantity;
        this.farmerId = farmerId;
    }

    // Getters and Setters

    public int getLivestockId() {
        return livestockId;
    }

    public void setLivestockId(int livestockId) {
        this.livestockId = livestockId;
    }

    public String getLivestockName() {
        return livestockName;
    }

    public void setLivestockName(String livestockName) {
        this.livestockName = livestockName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    // Additional Methods if needed
}
