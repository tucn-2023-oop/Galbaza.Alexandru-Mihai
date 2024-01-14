package com.example.farm2.Unused;

import java.math.BigDecimal;

public class MechanizedEquipment {
    private int mechanizedId;
    private String equipmentName;
    private String role;
    private BigDecimal fuelConsumption;
    private String fuelType;

    // Constructors
    public MechanizedEquipment() {
    }

    public MechanizedEquipment(String equipmentName, String role, BigDecimal fuelConsumption, String fuelType) {
        this.equipmentName = equipmentName;
        this.role = role;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;
    }

    // Getters and Setters
    public int getMechanizedId() {
        return mechanizedId;
    }

    public void setMechanizedId(int mechanizedId) {
        this.mechanizedId = mechanizedId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(BigDecimal fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    // Feel free to add any more methods or functionality here as needed
}
