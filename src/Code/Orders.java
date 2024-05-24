package Code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Question: Shouldnt we assign a customer id to an order?
//Question Where do we impment the order queue? In the order class or in the Foodora system?

public class Orders {
    // Member variables
    private String orderName;
    private int orderID;
    private Date orderDate;
    private List<String> items; // Assuming the order contains item names as Strings

    // Constructor
    public Orders(String orderName, int orderID, Date orderDate) {
        this.orderName = orderName;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.items = new ArrayList<>();
    }

    // Method to start an order
    public void startOrder() {
        System.out.println("Order started: " + orderName + " (ID: " + orderID + ")");
    }

    // Method to add an item to the order
    public void addToOrder(String item) {
        items.add(item);
        System.out.println("Added item to order: " + item);
    }

    // Method to summarize the order
    public void summarizeOrder() {
        System.out.println("Order Summary for " + orderName + " (ID: " + orderID + ")");
        System.out.println("Order Date: " + orderDate);
        System.out.println("Items in the Order:");
        for (String item : items) {
            System.out.println("- " + item);
        }
    }

    // Method to send the order
    public void sendOrder() {
        System.out.println("Order sent: " + orderName + " (ID: " + orderID + ")");
    }

    /* Getters and setters 
     * startOrder

addToOrder

summarizeOrder

sendOrder
     */
}
