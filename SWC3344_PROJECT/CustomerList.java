//import package
import java.text.DecimalFormat;

public class CustomerList 
{ //start of class
    //declaration
    private OrderInformation orderInfo;
    private CustomerInformation customerInfo;    
    
    public CustomerList(CustomerInformation customerInfo, OrderInformation orderInfo) {
        this.orderInfo = orderInfo;
        this.customerInfo = customerInfo;
    }

    // Getters for OrderInfo and CustomerInfo
    public OrderInformation getOrderInfo() {
        return orderInfo;
    }

    public CustomerInformation getCustomerInfo() {
        return customerInfo;
    }
    
    // Getter for retrieving some value from OrderInformation class
    public String getItem_Name() {
        return orderInfo.getItemName();
    }
    
    public double getTotalAmount() {
        return orderInfo.getItemPrice() * orderInfo.getQuantity();
    }
    
    // Getter to format total amount to 2 decimal places with "RM" prefix
    public String getFormattedTotalAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("RM#.00");
        return decimalFormat.format(getTotalAmount());
    }
}//end of class
