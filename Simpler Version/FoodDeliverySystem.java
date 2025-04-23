/*
   Name : Abdulrahman Slaghour
   University ID : 2435931
   Section : CS1
   Assignment number : #2
*/
import java.util.ArrayList;
import java.util.Arrays;

public class FoodDeliverySystem {
    //***PROPERTIES***
    private ArrayList<Person> persons;
    private ArrayList<Deliverable> deliverers;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Order> orders;

    //even though documentation hasn't specified orderTracker, it is needed for orderId
    //and order creation behaviour to follow documentation. For more details, see createOrder().
    //orderTracker value is initialized at 0 to act as a counter for the number of created orders in
    //the system.
    public static int orderTracker = 0;

    //***BEHAVIOURS***

    //UML specifies a default constructor for FoodDeliverySystem class.
    //There is no need to explicity add it since it should be implicitly
    //implemented because there aren't any constructors in this class, but as a
    //precaution I will implement it in case there are any future constructors added.

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

        //Since the documentation specifies that each ID is "Unique", I have interpreted this
        //to mean that the user is responsible for handling ID uniqueness.
        RegularCustomer newRegularCustomer = new RegularCustomer(id, name, phoneNumber);

        this.persons.add(newRegularCustomer);

        //since all required implementations for this method were completed, return success message
        //identical to output.txt format
        return "Regular Customer " + name + " added successfully.";
    }

    public String addGoldenCustomer(int id, String name, String phoneNumber, double monthlyFee, double discountRate) {
        //documentation states that this method does the following:
        //1- Creates a new GoldenMember object with a subscription fee and discount rate.
        //2- Adds the customer to the persons list.
        //3- Returns a confirmation message.

        //Since the documentation specifies that each ID is "Unique", I have interpreted this
        //to mean that the user is responsible for handling ID uniqueness.
        GoldenMember newGoldenMember = new GoldenMember(id, name, phoneNumber, monthlyFee, discountRate);

        this.persons.add(newGoldenMember);

        //since all required implementations for this method were completed, return success message
        //identical to output.txt format
        return "Golden Customer " + name + " added successfully.";
    }




    //**DELIVERY PERSONNEL MANAGEMENT**
    public String addDriver(int id, String name, String phoneNumber, int driverId, String vehicleType) {
        //documentation states that this method does the following:
        //1- Creates a Driver object and adds it to both the persons and deliverers lists.
        //2- Returns a confirmation message.

        //Since the documentation specifies that each ID is "Unique", I have interpreted this
        //to mean that the user is responsible for handling ID uniqueness.
        Driver newDriver = new Driver(id, name, phoneNumber, driverId, vehicleType);
        
        this.persons.add(newDriver);
        this.deliverers.add(newDriver);
        
        //since all required implementations for this method were completed, return success message
        //identical to output.txt format
        return "Driver " + name + " added successfully.";
    }

    public String addDrone(int droneId, double maxPayload, int batteryLevel) {
        //documentation states that this method does the following:
        //1- Creates a Drone object and adds it to the deliverers list.
        //2- Returns a confirmation message.

        //Since the documentation specifies that each ID is "Unique", I have interpreted this
        //to mean that the user is responsible for handling ID uniqueness.
        Drone newDrone = new Drone(droneId, maxPayload, batteryLevel);

        this.deliverers.add(newDrone);

        //since all required implementations for this method were completed, return success message
        //identical to output.txt format
        return "Drone "+ droneId + " added successfully.";
    }

    // **RESTAURANT AND MENU MANAGEMENT**
    public String addRestaurant(int restaurantId, String name, String address) {
        // documentation states that this method does the following:
        // 1- Creates a Restaurant object and adds it to the restaurants list
        // 2- Returns a confirmation message

        //Since the documentation specifies that each ID is "Unique", I have interpreted this
        //to mean that the user is responsible for handling ID uniqueness.
        Restaurant newRestaurant = new Restaurant(restaurantId, name, address);

        this.restaurants.add(newRestaurant);

        //since all required implementations for this method were completed, return success message
        //identical to output.txt format
        return "Restaurant "+ name +" added successfully.";
    }

    public String addMenuItem(int restaurantId, String menuItem) {
        // documentation states that this method does the following:
        // 1- Searches for a restaurant by its restaurantId and adds a menu item to it.
        // 2- Returns a message indicating whether the item was successfully added or if
        // the restaurantId was not found.

        //Since ArrayList restaurants isn't guaranteed to be ordered, then use linear search
        //to find the restaurant by the given restaurantId
        for (Restaurant restaurant : this.restaurants) {
            //since we are comparing two primitive int, is should be okay to use == instead of
            //.equals()
            if (restaurant.getRestaurantId() == restaurantId) {
                restaurant.addMenuItem(menuItem);
                return "Menu item \'"+ menuItem +"\' added to restaurant "+restaurant.getName();
                //once the restaurant is found, loop should terminate and return confirmation
                //message identical to given format in output.txt
            }
        }

        //if code reaches this point that means restaurant wasn't found matching given restaurantId,
        //so documentation says that this method should return a message indicating restaurantId wasn't
        //found. The problem is that the given output file hasn't given an example format for the fail
        //message, so I will make my own:
        return "restaurantId: " + restaurantId + " was not found.";
    }

    // **ORDER MANAGEMENT**
    public String createOrder(Customer customer, int restaurantId, String[] items, double deliveryCost) {
        // documentation states that this method does the following:
        // 1- Finds the specified restaurant and creates a new Order object.
        // 2- Adds food items to the order and sets the delivery cost (applying a
        // discount if the customer is a GoldenMember).
        // 3- Adds the order to the orders list and returns a success message.

        
        //initialize the orderRestaurant as null to act as a failsafe in case the 
        //restaurant isn't found.
        Restaurant orderRestaurant = null;

        //find the restaurant with the given restaurantId with a linear search
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.getRestaurantId() == restaurantId) {
                //once the restaurant is found, orderRestaurant will now reference
                //and point to it, and the for loop will break.
                orderRestaurant = restaurant;
                break;
            }
        }

        //check if there was a restaurant found with matching id to restaurantId via
        //orderRestaurant's reference. If it is still null, then the restaurant wasn't
        //found.
        if (orderRestaurant == null) {
            //the issue is the same as the previous method where a message format wasn't
            //provided for this case in output.txt, so I will improvise one:
            return "restaurantId: " + restaurantId + " was not found.";
        }

        //if code has reached this point, then the restaurant was found and we can continue
        //under that assumption

        //orderId is required to make an Order. Since no orderId is given and documentation hints
        //at no relevant variable to reference, then a static variable is needed in FoodDeliverySystem
        //to keep track of unique orderIds.
        
        //incrementing orderTracker before creation of newOrder because at the start of FoodDeliverySystem
        //orderTracker is initialized at 0.
        orderTracker++;
        Order newOrder = new Order(orderTracker, customer, orderRestaurant);

        //since the Order class constructer doesn't initialize ArrayList foodItems, then
        //I will use the method setFoodItems()

        //method setFoodItems() only accepts ArrayList<String>, so converting String[] items
        //into ArrayList<String> is necessary before calling the method. We can do this through
        //the method asList() as described in slide 97 of CH11.
        ArrayList<String> itemsAsList = new ArrayList<>(Arrays.asList(items));

        //now we pass itemsAsList into setFoodItems()
        newOrder.setFoodItems(itemsAsList);

        //we pass the deliveryCost as given because setDeliveryCost() checks the instanceof the
        //customer attached to the Order object and gives a discount as specified by documentation.
        newOrder.setDeliveryCost(deliveryCost);

        this.orders.add(newOrder);

        //since all required implementations for this method were completed, return success message
        //identical to output.txt format
        return "Order "+orderTracker+" created successfully for "+customer.getName();
    }

    
    public String assignDeliverer(int orderId, Deliverable deliverer) {
        // documentation states that this method does the following:
        // 1- Searches for an order by orderId and assigns a deliverer.
        // 2- Checks if the deliverer exists in the system before assigning.
        // 3- Returns a message confirming the assignment.

        //use for loop to find order via orderId

        //assign foundOrder as null to later verify whether order is found in ArrayList orders.
        Order foundOrder = null;
        for (Order currentOrder : this.orders) {
            //if currentOrderId matches orderId, then assign currentOrder to foundOrder
            //and break the for loop.
            if (currentOrder.getOrderId() == orderId) {
                foundOrder = currentOrder;
                break;
            }
        }

        if (foundOrder == null) {
            return "orderId: " + orderId + " was not found.";
        }

        //if code has reached this point, then the order was found and we can continue
        //under that assumption

        //before assigning the deliverer to foundOrder, user should have passed delivererId
        //into getDelivererById() to get the deliverer object. getDelivererById() should have
        //checked if deliverer is in ArrayList deliverers and returned a value based on its existance.
        
        //check if method returned null or an object before proceeding
        if (deliverer == null) {
            return "Deliverer not found.";
        }

        //if code has reached this point, then the deliverer was found and we can continue
        //under that assumption

        foundOrder.setDeliverer(deliverer);

        //before returning success message, determain the deliverer's type to use the appropriate
        //methods and send the right success message.

        if (deliverer instanceof Driver) {
            return "Driver "+((Person)deliverer).getName()+" assigned to Order "+orderId;
        } else if (deliverer instanceof Drone) {
            return "Drone "+((Drone)deliverer).getDroneId()+" assigned to Order "+orderId;
        }

        //even though code shouldn't reach this point, a default return message should be returned so
        //the compiler doesn't throw an error.
        return "assignDeliverer to order "+orderId+" failed.";
    }

    // **REPORTING**
    public String printOrdersByDeliverer(Deliverable deliverer) {
        // documentation states that this method does the following:
        // 1- Prints all orders assigned to a specific deliverer (either a Driver or a
        // Drone).
        // 2- Formats the output in a structured way.
        // 3- Returns a message if no orders are found.

        //a StringBuilder will be used for ease of readability
        StringBuilder allOrders = new StringBuilder();

        //initialize variable orderTracker to later verify whether any orders were delivered by deliverer
        int orderTracker = 0;
        if (deliverer instanceof Driver) {
            allOrders.append("-----Orders delivered by Driver "+((Driver)deliverer).getName()+" (ID: "+((Driver)deliverer).getDriverId()+")--------\n");

            //parse through orders and check if currentOrder's deliverer is the same as passed deliverer
            for (Order currentOrder : this.orders) {
                // save order's deliverer in assignedDeliverer to increase readabilty
                Deliverable assignedDeliverer = currentOrder.getDeliverer();

                //append order details according to output.txt
                if (deliverer.equals(assignedDeliverer)) {
                    //increment orderTracker to keep track of orders this deliverer has
                    orderTracker++;
                    allOrders.append("===============================================================\n");
                    allOrders.append("Order ID   Customer        Restaurant           Delivery Cost  \n");

                    //Since given output.txt doesn't round up the deliveryCost, then truncate deliveryCost() by multiplying by 100 and using
                    //Math.floor() to get rid of the rest of the decimals after the point, then divide by 100 to prep number to get insertted
                    //as the correct format to match faculty given output.txt.
                    double truncatedDeliveryCost = Math.floor(currentOrder.getDeliveryCost() * 100);
                    truncatedDeliveryCost = truncatedDeliveryCost / 100;
                    allOrders.append(String.format("%-10s %-15s %-20s %-13.2f\n", currentOrder.getOrderId(), currentOrder.getCustomer().getName(), currentOrder.getRestaurant().getName(), truncatedDeliveryCost));
                    allOrders.append("-----------------------------Items---------------------------\n");
                    
                    //for loop to parse through items in order

                    //initialize itemTracker as 1 and increment after each item
                    int itemTracker = 1;
                    for (String item : currentOrder.getFoodItems()) {
                        allOrders.append(itemTracker +"-"+ item+"\n");
                        itemTracker++;
                    }
                    allOrders.append("==============================End==============================\n");
                }
            }
        } else if (deliverer instanceof Drone) {
            //output.txt hasn't outlined an example for when this method is called for a Drone, do some details will
            //be improvised, such as the first line of the StringBuilder allOrders
            allOrders.append("-----Orders delivered by Drone "+((Drone)deliverer).getDroneId()+" (ID: "+((Drone)deliverer).getDroneId()+")--------\n");

            //parse through orders and check if currentOrder's deliverer is the same as passed deliverer
            for (Order currentOrder : this.orders) {
                // save order's deliverer in assignedDeliverer to increase readabilty
                Deliverable assignedDeliverer = currentOrder.getDeliverer();

                //append order details according to output.txt
                if (deliverer.equals(assignedDeliverer)) {
                    //increment orderTracker to keep track of orders this deliverer has
                    orderTracker++;
                    allOrders.append("===============================================================\n");
                    allOrders.append("Order ID   Customer        Restaurant           Delivery Cost  \n");
                    allOrders.append(String.format("%-10s %-15s %-20s %-13.2f\n", currentOrder.getOrderId(), currentOrder.getCustomer().getName(), currentOrder.getRestaurant().getName(), currentOrder.getDeliveryCost()));
                    allOrders.append("-----------------------------Items---------------------------\n");
                    
                    //for loop to parse through items in order

                    //initialize itemTracker as 1 and increment after each item
                    int itemTracker = 1;
                    for (String item : currentOrder.getFoodItems()) {
                        allOrders.append(itemTracker +"-"+ item+"\n");
                        itemTracker++;
                    }
                    allOrders.append("==============================End==============================\n");
                }
            }  
        }

        //return formatted orders if orderTracker > 0
        if (orderTracker > 0) {
            return allOrders.toString();
        }

        //if code has reached this point, then there were no orders found in relation to deliverer
        return "No orders found for deliverer.";

    }

    public String getTotalCostByDeliverer(int delivererId) {
        // documentation states that this method does the following:
        // 1- Calculates the total earnings from deliveries made by a specific
        // deliverer.
        // 2- Returns a formatted message indicating the total amount.

        //Use linear search through ArrayList orders since there is no other way to
        //check assigned orders to a specific deliverer.

        //initialize a totalCost to later use in the formatted message
        double totalCost = 0;

        //use the passed delivererId to get the intended deliverer using getDelivererById().
        //this will later help with cases where no deliverer is found matching the passed
        //delivererId.
        Deliverable passedDeliverer = getDelivererById(delivererId);

        for (Order currentOrder : this.orders) {
            //save order's deliverer in assignedDeliverer to increase readabilty
            Deliverable assignedDeliverer = currentOrder.getDeliverer();

            if (passedDeliverer.equals(assignedDeliverer)) {
                    totalCost += currentOrder.getDeliveryCost();
            }
        }

        //according to output.txt, a different formatted message is returned depending on the
        //deliverer's type
        if (passedDeliverer instanceof Driver) {
            //return a formatted string since output.txt only has 2 decimal places after the decimal point
            return String.format("The Driver with ID "+delivererId+" has earned $%2.2f for deliveries.", totalCost);
        } else if (passedDeliverer instanceof Drone) {
            return String.format("The Drone with ID "+delivererId+" has earned $%2.2f for deliveries.", totalCost);
        }

        //if code has reached this point, then there wasn't a match in the system with the given delivererId,
        //so return a fail message.
        return "Failed to find deliverer with delivererId: "+delivererId;
        
    }

    public String printOrdersByCustomer(int customerId) {
        // documentation states that this method does the following:
        // 1- Finds all orders placed by a specific customer.
        // 2- Displays order details, including restaurant name, delivery cost, items,
        // and assigned deliverer.
        // 3- Returns a message if no orders are found.

        //in order to get full customer details, use getCustomerById() and save returned customer in
        //passedCustomer.
        Customer passedCustomer = getCustomerById(customerId);

        //save customer's status in boolean variable for future verification and appropriate method calls
        boolean passedCustomerIsGolden = (passedCustomer instanceof GoldenMember ? true : false);

        //a StringBuilder will be used for ease of readability
        StringBuilder allOrders = new StringBuilder();

        allOrders.append("------------- Orders for Customer: "+passedCustomer.getName()+" (ID: "+passedCustomer.getId()+")------------\n");

        //initialize orderTracker to verify whether any orders were rfound for passedCustomer
        int orderTracker = 0;
        //use for loop to parse through orders and find related orders to passedCustomer
        for (Order currentOrder : this.orders) {
            //initialize itemTracker as 1 after each iteration for item indexing when listing order's foodItems
            int itemTracker = 1;
            
            //check if the currentOrder is the customer's
            if (passedCustomer.equals(currentOrder.getCustomer())) {
                //increment orderTracker to later return the orders related to passedCustomer
                orderTracker++;
                allOrders.append("-------------------------------------------------------------\n");
                allOrders.append(String.format("Order ID      : %s\n",currentOrder.getOrderId()));

                //ternary operator is used to limit duplication of code
                allOrders.append(String.format("Customer Type : %s\n",(passedCustomerIsGolden ? "Golden" : "Regular")));
                allOrders.append(String.format("Restaurant    : %s\n",currentOrder.getRestaurant().getName()));
                
                //ternary operator is necessary since getDiscountRate() is a method only available to GoldenMembers, so verifying
                //the instance is important before calling the method.
                allOrders.append(String.format("Discount Rate : %.1f\n", (passedCustomerIsGolden ? ((GoldenMember) passedCustomer).getDiscountRate() : 0.0)));
                allOrders.append(String.format("Delivery Cost : %.2f\n", currentOrder.getDeliveryCost()));
                allOrders.append("Items:\n");

                //iterate through currentOrder's items using for loop
                for (String item : currentOrder.getFoodItems()) {
                    //\n is necessary so output stays formatted according to output.txt
                    allOrders.append("   "+itemTracker+". "+item+"\n");

                    //increment itemTracker to keep format of message in line with output.txt
                    itemTracker++;
                }

                //save currentOrder's deliverer to later use
                Deliverable currentOrderDeliverer = currentOrder.getDeliverer();
                //save each order's deliverer status as a Driver or Drone to later use in Delivered By:
                boolean delivererIsDriver = (currentOrderDeliverer instanceof Driver ? true : false);

                //use delivererIsDriver to change message according to the deliverer's status via ternary operator
                allOrders.append("Delivered by  : "+(delivererIsDriver ? "Driver " +((Driver) currentOrderDeliverer).getName()+" (ID: "+((Driver) currentOrderDeliverer).getDriverId()+")\n" : "Drone (ID: "+((Drone) currentOrderDeliverer).getDroneId()+")\n"));
                allOrders.append("-------------------------------------------------------------\n");
            }
        }

        //if any orders were found, return the StringBuilder as a string using toString()
        if (orderTracker > 0) {
            return allOrders.toString();
        }

        //if code reaches this point, then there weren't any orders found, so return a fail message
        return "No orders found.";
    }

    // **HELPER METHODS**
    public Customer getCustomerById(int customerId) {
        // documentation states that this method does the following:
        // Searches for a Customer object by its id in the persons list and returns it.

        //use for loop to parse through the ArrayList persons
        for (Person currentPerson : this.persons) {
            //since ArrayList persons contains both Customers and Drivers, check
            //whether the currentPerson is a Driver, since this method can only
            //return Customer objects
            if (currentPerson instanceof Driver) {
                continue;
            }

            //since object isn't an instanceof Driver, then it is a Customer
            int currentCustomerID = currentPerson.getId();

            if (currentCustomerID == customerId) {
                return (Customer) currentPerson;
            }
        }

        //if code has reached this point, then there is no Customer with matching ID. Since documentation
        //hasn't specified what should happen at this point, I will return a null type and print a fail message
        //to terminal.

        System.out.println("Failed to getCustomerById(int "+customerId+")");
        return null;
    }
    
    public Deliverable getDelivererById(int delivererId) {
        // documentation states that this method does the following:
        // Searches for a deliverer (Driver or Drone) in the deliverers list using delivererId and returns it.

        //use for loop to parse through ArrayList deliverers
        for (Deliverable deliverer : this.deliverers) {
            //since Driver and Drone objects inside ArrayList deliverers use different methods to retrieve their
            //IDs, check what instance the object is before using the corresponding method.
            if (deliverer instanceof Driver) {
                if (((Driver)deliverer).getDriverId() == delivererId) {
                    return deliverer;
                }
            } else if (deliverer instanceof Drone) {
                if (((Drone)deliverer).getDroneId() == delivererId) {
                    return deliverer;
                }
            }
        }

        //if code reaches this point then there wasn't a match via delivererId. So just return a null object
        //and print a message in the terminal detailing failure to find match in ArrayList deliverers
        System.out.println("delivererId: " + delivererId + "not found.");
        return null;
    }

    public ArrayList<Person> getPersons() {
        // documentation states that this method does the following:
        // Returns the list of all persons in the system.
    
        return this.persons;
    }

    public ArrayList<Deliverable> getDeliverers() {
        // documentation states that this method does the following:
        // Returns the list of all deliverers in the system.

        return this.deliverers;
    }
}