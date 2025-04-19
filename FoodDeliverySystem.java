import java.util.ArrayList;
public class FoodDeliverySystem {
    //***PROPERTIES***
    private ArrayList<Person> persons;
    private ArrayList<Deliverable> deliverers;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Order> orders;

    //***BEHAVIOURS***

    //UML specifies a default constructor for FoodDeliverySystem class.
    //There is no need to explicity add it since it should be implicitly
    //implemented because there aren't any constructors implemented, but as a
    //precaution I will implement it in case there are any future constructors.

    public FoodDeliverySystem() {
        this.persons = new ArrayList<Person>();
        this.deliverers = new ArrayList<Deliverable>();
        this.restaurants = new ArrayList<Restaurant>();
        this.orders = new ArrayList<Order>();
    }

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

    // **RESTAURANT AND MENU MANAGEMENT**
    public String addRestaurant(int restaurantId, String name, String address) {
        // documentation states that this method does the following:
        // 1- Creates a Restaurant object and adds it to the restaurants list
        // 2- Returns a confirmation message
    }

    public String addMenuItem(int restaurantId, String menuItem) {
        // documentation states that this method does the following:
        // 1- Searches for a restaurant by its restaurantId and adds a menu item to it.
        // 2- Returns a message indicating whether the item was successfully added or if
        // the restaurantId was not found.
    }

    // **ORDER MANAGEMENT**
    public String createOrder(Customer customer, int restaurantId, String[] items, double deliveryCost) {
        // documentation states that this method does the following:
        // 1- Finds the specified restaurant and creates a new Order object.
        // 2- Adds food items to the order and sets the delivery cost (applying a
        // discount if the customer is a GoldenMember).
        // 3- Adds the order to the orders list and returns a success message.
    }

    public String assignDeliverer(int orderId, Deliverable deliverer) {
        // documentation states that this method does the following:
        // 1- Searches for an order by orderId and assigns a deliverer.
        // 2- Checks if the deliverer exists in the system before assigning.
        // 3- Returns a message confirming the assignment.
    }

    // **REPORTING**
    public String printOrdersByDeliverer(Deliverable deliverer) {
        // documentation states that this method does the following:
        // 1- Prints all orders assigned to a specific deliverer (either a Driver or a
        // Drone).
        // 2- Formats the output in a structured way.
        // 3- Returns a message if no orders are found.
    }

    public String getTotalCostByDeliverer(int delivererId) {
        // documentation states that this method does the following:
        // 1- Calculates the total earnings from deliveries made by a specific
        // deliverer.
        // 2- Returns a formatted message indicating the total amount.
    }

    public String printOrdersByCustomer(int customerId) {
        // documentation states that this method does the following:
        // 1- Finds all orders placed by a specific customer.
        // 2- Displays order details, including restaurant name, delivery cost, items,
        // and assigned deliverer.
        // 3- Returns a message if no orders are found.
    }

    // **HELPER METHODS**
    public Customer getCustomerById(int customerId) {
        // documentation states that this method does the following:
        // Searches for a Customer object by its id in the persons list and returns it.
    }
    
    public Deliverable getDelivererById(int delivererId) {

    }

    public ArrayList<Person> getPersons() {
        // documentation states that this method does the following:
        // Returns the list of all persons in the system.
    
    }

    public ArrayList<Deliverable> getDeliverers() {
        // documentation states that this method does the following:
        // Returns the list of all deliverers in the system.
    }
}
