package CMPE232;

public class Equipment {
    private int equipmentID;
    private String name;
    private String type;
    private String condition;
    private boolean availability;

    // Constructor
    public Equipment(int equipmentID, String name, String type, String condition, boolean availability) {
        this.equipmentID = equipmentID;
        this.name = name;
        this.type = type;
        this.condition = condition;
        this.availability = availability;
    }

    // Getters and Setters
    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

