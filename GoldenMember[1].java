public class GoldenMember extends Customer {
    //***PROPERTIES***
    //some properties inherited from Customer
    
    private double monthlySubscriptionFee;
    private double discountRate;
    
    //***BEHAVIOURS***
    
    public GoldenMember(int id, String name, String phoneNumber, double monthlySubscriptionFee, double discountRate) {
        super(id, name, phoneNumber);
        this.monthlySubscriptionFee = monthlySubscriptionFee;
        this.discountRate = discountRate;
    }
    
    public double getMonthlySubscriptionFee() {
        return this.monthlySubscriptionFee;
    }
    
    public void setMonthlySubscriptionFee(double monthlySubscriptionFee) {
        this.monthlySubscriptionFee = monthlySubscriptionFee;
    }
    
    public double getDiscountRate() {
        return this.discountRate;
    }
    
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}