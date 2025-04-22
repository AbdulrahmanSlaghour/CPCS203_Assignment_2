/*
   Name : Abdulrahman Slaghour
   University ID : 2435931
   Section : CS1
   Assignment number : #2
*/
import java.util.ArrayList;
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
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Restaurant getRestaurant() {
        return this.restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public ArrayList<String> getFoodItems() {
        return this.foodItems;
    }
    
    public void setFoodItems(ArrayList<String> foodItems) {
        this.foodItems = foodItems;
    }
    
    public double getDeliveryCost() {
        return this.deliveryCost;
    }
    
    public void setDeliveryCost(double deliveryCost) {
        //according to p.6 of the documentation, setDeliveryCost() relies on the customer's status:
        //if customer is a GoldenMember then apply a discount to deliveryCost and set its value,
        //if not then set deliveryCost without discount.
        
        //check whether the customer is a GoldenMember using instanceof
        if (this.customer instanceof GoldenMember) {
            //since the discountRate is a percentage of sale, multiply discountRate and
            //deliveryCost to get the discount amount, then subtract it from the deliveryCost
            this.deliveryCost = deliveryCost - (((GoldenMember) this.customer).getDiscountRate() * deliveryCost);
            
        } else {
            this.deliveryCost = deliveryCost;
        }
    }
    
    public Deliverable getDeliverer() {
        return this.deliverer;
    }
    
    public void setDeliverer(Deliverable deliverer) {
        this.deliverer = deliverer;
    }
    
    public void addFoodItem(String item) {
        //since documentation didn't specify a specific index to add item to ArrayList, then add it
        //to the end of the ArrayList
        this.foodItems.add(item);
    }
}