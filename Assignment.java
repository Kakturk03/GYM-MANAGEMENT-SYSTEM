package CMPE232;

public class Assignment {
    private int trainerID;
    private int classID;

    // Constructor
    public Assignment(int trainerID, int classID) {
        this.trainerID = trainerID;
        this.classID = classID;
    }

    // Getters and Setters
    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
}

