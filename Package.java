package CMPE232;

public class Package {
    private int packageID;
    private String name;
    private double amount;
    private String type;
    private String password;

    // Constructor
    public Package(int packageID, String name, double amount, String type, String password) {
        this.packageID = packageID;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.password = password;
    }

    // Getters and setters
    public int getPackageID() { return packageID; }
    public void setPackageID(int packageID) { this.packageID = packageID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}


