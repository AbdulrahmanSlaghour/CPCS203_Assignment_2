public class Drone implements Deliverable {
    //***PROPERTIES***
    
    private int droneId;
    private double maxPayload;
    private int batteryLevel;
    
    //***BEHAVIOURS***
    
    public Drone(int droneId, double maxPayload, int batteryLevel) {
         this.droneId = droneId;
         this.maxPayload = maxPayload;
         this.batteryLevel = batteryLevel;
    }
    
    public int getDroneId() {
        return this.droneId;
    }
    
    public void setDroneId(int droneId) {
        this.droneId = droneId;
    }
    
    public double getMaxPayload() {
        return this.maxPayload;
    }
    
    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }
    
    public int getBatteryLevel() {
        return this.batteryLevel;
    }
    
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}