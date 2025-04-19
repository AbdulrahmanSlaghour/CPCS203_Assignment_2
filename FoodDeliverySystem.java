import java.util.ArrayList;
public class FoodDeliverySystem {
    //***PROPERTIES***
    private ArrayList<Person> persons;
    private ArrayList<Deliverable> deliverers;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Order> orders;

    //***BEHAVIOURS***

    //**CUSTOMER MANAGEMENT**
    public String addRegularCustomer(int id, String name, String phoneNumber) {
        //documentation states that this method does the following:
        //1- Creates a new RegularCustomer object and .add() to the persons ArrayList.
        //2- Returns a success message confirming the addition.
    }

    public String addGoldenCustomer(int id, String name, String phoneNumber, double monthlyFee, double discountRate) {
        //documentation states that this method does the following:
        //1- Creates a new GoldenMember object with a subscription fee and discount rate.
        //2- Adds the customer to the persons list.
        //3- Returns a confirmation message.
    }

    //**DELIVERY PERSONNEL MANAGEMENT**
    public String addDriver(int id, String name, String phoneNumber, int driverId, String vehicleType) {
        //documentation states that this method does the following:
        //1- Creates a Driver object and adds it to both the persons and deliverers lists.
        //2- Returns a confirmation message.
    }

    public String addDrone(int droneId, double maxPayload, int batteryLevel) {
        //documentation states that this method does the following:
        //1- Creates a Drone object and adds it to the deliverers list.
        //2- Returns a confirmation message.
    }

    //**RESTAURANT AND MENU MANAGEMENT**
}
