package CMPE232;

public class Enrollment {
    private int memberID;
    private int classID;

    // Constructor
    public Enrollment(int memberID, int classID) {
        this.memberID = memberID;
        this.classID = classID;
    }

    // Getters and Setters
    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
}

