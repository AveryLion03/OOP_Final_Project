package food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    private Orders order;
    private String orderName;
    private int orderID;
    private Date orderDate;

    @BeforeEach
    void setUp() {
        orderName = "TestOrder";
        orderID = 1;
        orderDate = new Date();
        order = new Orders(orderName, orderID, orderDate);
    }

    @Test
    void testStartOrder() {
        System.out.println("Running testStartOrder...");
        order.startOrder();
        assertNotNull(order);
        System.out.println("Order started successfully.");
    }

    @Test
    void testAddToOrder() {
        System.out.println("Running testAddToOrder...");
        String item = "Burger";
        order.addToOrder(item);
        assertTrue(order.getItems().contains(item));
        System.out.println("Item added to order: " + item);
    }

    @Test
    void testSummarizeOrder() {
        System.out.println("Running testSummarizeOrder...");
        order.addToOrder("Burger");
        order.addToOrder("Fries");
        order.summarizeOrder();
        assertEquals(2, order.getItems().size());
        System.out.println("Order summarized successfully.");
    }

    @Test
    void testSendOrder() {
        System.out.println("Running testSendOrder...");
        order.sendOrder();
        assertNotNull(order);
        System.out.println("Order sent successfully.");
    }
}
