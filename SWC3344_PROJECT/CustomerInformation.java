// CustomerInformation class
public class CustomerInformation 
{//start of class
    //declaraion
    private String custId;
    private String custName;
    private int tableNumber;

    // Constructor with parameter
    public CustomerInformation(String custId, String custName, int tableNumber) {
        this.custId = custId;
        this.custName = custName;
        this.tableNumber = tableNumber;
    }

    // Accessors (getters)
    public String getCustId() {
        return custId;
    }

    public String getCustName() {
        return custName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    // Mutators (setters)
    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    //display method
    public String toString() 
    {
        return "Customer ID: " + custId  +
        "\nCustomer Name: " + custName  +
        "\nTable Number: " + tableNumber + "\n";
    }
} //end of class