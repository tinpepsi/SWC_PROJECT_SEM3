//import package
import java.text.DecimalFormat;

// OrderInformation class
public class OrderInformation 
{//start of class
    //declaration 
    private String orderId;
    private String itemName;
    private double itemPrice;
    private int quantity;
    private String orderTime;

    // Constructor with parameter
    public OrderInformation(String orderId, String itemName, double itemPrice, int quantity, String orderTime) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.orderTime = orderTime;
    }

    // Accessors (getters)
    public String getOrderId() {
        return orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrderTime() {
        return orderTime;
    }

    // Mutators (setters)
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    // Getter to format total amount to 2 decimal places with "RM" prefix
    public String formattedItemPrice() {
        DecimalFormat df = new DecimalFormat("RM#.00");
        return df.format(itemPrice);  // Formats itemPrice to two decimal places
    }

    //display method
    public String toString() {
        return "Customer Order ID: " + orderId + 
        "\nOrder Item: " + itemName + 
        "\nPrice: " + formattedItemPrice() + // Use formattedItemPrice() here
        "\nQuantity: " + quantity + 
        "\nOrder Time: " + orderTime;
    }
}//end of class
