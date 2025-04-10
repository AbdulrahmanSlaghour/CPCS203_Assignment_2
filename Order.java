public class Order {
    //***PROPERTIES***
    
    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private ArrayList<String> foodItems;
    private double deliveryCost;
    private Deliverable deliverer;
    
    //***BEHAVIOURS***
    
    public Order(int orderId, Customer customer, Restaurant restaurant) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public 
}