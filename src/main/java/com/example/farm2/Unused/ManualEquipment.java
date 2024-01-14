package com.example.farm2.Unused;

public class ManualEquipment {
    private int manualId;
    private int equipmentId;
    private String equipmentName;

    public ManualEquipment() {
        // Default constructor for potential use
    }

    public ManualEquipment(int manualId, int equipmentId, String equipmentName) {
        this.manualId = manualId;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
    }

    public int getManualId() {
        return manualId;
    }

    public void setManualId(int manualId) {
        this.manualId = manualId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

}
