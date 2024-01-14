package com.example.farm2.Unused;

public class Equipment {
    private int equipmentId;
    private String role;
    private int farmerId;

    // Constructors
    public Equipment() {
    }

    public Equipment(String role, int farmerId) {
        this.role = role;
        this.farmerId = farmerId;
    }

    // Getters and Setters
    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    // You can add any additional methods or fields as needed
}
