public class Sales {
    private String customerName;
    private double orderedAmount;
    private String date;

    public Sales(String customerName, double orderedAmount, String date) {
        this.customerName = customerName;
        this.orderedAmount = orderedAmount;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(double orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}