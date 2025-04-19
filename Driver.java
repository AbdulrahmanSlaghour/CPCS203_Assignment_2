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
    
    
    //A clarification email was sent telling all students to disregard calculateEarnings() and NOT TO USE IT OR REFER TO IT in implementation.
    //public double calculateEarnings(double base, double distance) {
        //Documentation specifies that this method: "Calculates the driver's earnings based on a base fee and a per-distance charge"
        //but a clarification email was sent to all students which details: "driver earnings = sum of delivery costs of all orders completed by that driver."
        //But since there is no way to obtain order details in Driver, this method was commented out and will not be used
        //in accordance with the clarification email sent to all CPCS203 students.
    //}
}
