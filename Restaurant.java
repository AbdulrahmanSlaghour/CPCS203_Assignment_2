public class Restaurant {
    //***PROPERTIES***
    private int restaurantId;
    private String name;
    private String address;
    private ArrayList<String> menuItems;

    //***BEHAVIOURS***
    public Restaurant(int restaurantId, String name, String address) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
    }
    
    public int getRestaurantId() {
        return this.restaurantId;
    }
    
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public ArrayList<String> getMenuItems() {
        return this.menuItems;
    }
    
    public void setMenuItems(ArrayList<String> menuItems) {
        this.menuItems = menuItems;
    }
    
    public void addMenuItem(String Item) {
        this.menuItems.add(Item);
    }
}