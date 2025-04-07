public class Driver extends Person implements Deliverable {
    //***PROPERTIES***
    //some properties inheritted from Person
    
    private int driverId;
    private String vehicleType;
    
    //***BEHAVIOURS***
    
    public Driver(int id, String name, String phoneNumber, int driverId, String vehicleType) {
        super(id, name, phoneNumber);
        this.driverId = driverId;
        this.vehicleType = vehicleType;
    }
    
    public int getDriverId() {
        return this.driverId;
    }
    
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    
    public String getVehicleType() {
        return this.vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public double calculateEarnings(double base, double distance) {
        //Calculates hte driver's earnings based on a base fee and a per-distance charge
    }
}
