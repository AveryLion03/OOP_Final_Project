package user;

import Code.*;
import food.Dishes;
import food.Meal;
import food.Order;
import user.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//NOT ALL TESTS PASS AS OF RN
class CustomerTest {

    private Customer customer;
    private Location location;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        // Reset the counter before each test to ensure consistent IDs
        resetUserCounter();
        location = new Location(40.7128, -74.0060);
        customer = new Customer("johndoe", "password123", "Customer", "John", "Doe", "john.doe@example.com", "+1(234)567-8901", location);
        restaurant = new Restaurant("burgerpalace_admin", "burger123", "Restaurant", "Burger Palace", location);
    }

    @Test
    void testCustomerCreation() {
        System.out.println("Running testCustomerCreation...");
        assertEquals(1, customer.getID());
        System.out.println("Customer ID: " + customer.getID());
        assertEquals("johndoe", customer.getUsername());
        System.out.println("Username: " + customer.getUsername());
        assertEquals("password123", customer.getPassword());
        System.out.println("Password: " + customer.getPassword());
        assertEquals("Customer", customer.getUserType());
        System.out.println("User Type: " + customer.getUserType());
        assertEquals("John", customer.getName());
        System.out.println("Name: " + customer.getName());
        assertEquals("Doe", customer.getSurname());
        System.out.println("Surname: " + customer.getSurname());
        assertEquals("john.doe@example.com", customer.getEmail());
        System.out.println("Email: " + customer.getEmail());
        assertEquals("+1(234)567-8901", customer.getCellNumber());
        System.out.println("Cell Number: " + customer.getCellNumber());
        assertEquals(location, customer.getLoc());
        System.out.println("Location: " + customer.getLoc());
        assertEquals("basic", customer.getFidelity());
        System.out.println("Fidelity: " + customer.getFidelity());
        assertEquals(0, customer.getPoints());
        System.out.println("Points: " + customer.getPoints());
    }

    @Test
    void testSetEmail() {
        System.out.println("Running testSetEmail...");
        customer.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", customer.getEmail());
        System.out.println("Updated Email: " + customer.getEmail());
    }

    @Test
    void testSetCellNumber() {
        System.out.println("Running testSetCellNumber...");
        customer.setCellNumber("+1(987)654-3210");
        assertEquals("+1(987)654-3210", customer.getCellNumber());
        System.out.println("Updated Cell Number: " + customer.getCellNumber());
    }

    @Test
    void testSetLoc() {
        System.out.println("Running testSetLoc...");
        Location newLocation = new Location(34.0522, -118.2437);
        customer.setLoc(newLocation);
        assertEquals(newLocation, customer.getLoc());
        System.out.println("Updated Location: " + customer.getLoc());
    }

    @Test
    void testSetFidelity() {
        System.out.println("Running testSetFidelity...");
        customer.setFidelity("point");
        assertEquals("basic", customer.getFidelity());
        System.out.println("Updated Fidelity: " + customer.getFidelity());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setFidelity("invalid");
        });
        System.out.println("Exception: " + exception.getMessage());
        assertEquals("Invalid fidelity value. Options are none, basic, point, or lottery.", exception.getMessage());
    }

    @Test
    void testPointsDiscount() {
        System.out.println("Running testPointsDiscount...");
        customer.setFidelity("point");
        assertFalse(customer.pointsDiscount(50));
        System.out.println("Points after 50: " + customer.getPoints());
        assertFalse(customer.pointsDiscount(40));
        System.out.println("Points after 90: " + customer.getPoints());
        assertTrue(customer.pointsDiscount(10));
        System.out.println("Points after 100: " + customer.getPoints());
    }
    /*

    @Test
    void testOrderMethods() {
        System.out.println("Running testOrderMethods...");
        customer.createOrder("Order1", restaurant);
        assertNotNull(customer.activeOrder);
        System.out.println("Active Order: " + customer.activeOrder.getOrderName());
        assertEquals(restaurant, customer.getRestaurant("Order1"));
        System.out.println("Restaurant for Active Order: " + customer.getRestaurant("Order1").getName());

        

        customer.finalizeOrder("2024-01-01");
        assertNull(customer.activeOrder);
        System.out.println("Active Order after finalizing: " + customer.activeOrder);
        assertTrue(customer.pastOrders.stream().anyMatch(order -> order.getOrderName().equals("Order1")));
        System.out.println("Past Orders: " + customer.pastOrders);

        assertTrue(customer.checkOrder("Order1"));
        System.out.println("Check Order 'Order1': " + customer.checkOrder("Order1"));
        assertTrue(customer.findOrder("Order1"));
        System.out.println("Find Order 'Order1': " + customer.findOrder("Order1"));
    }
	*/
    // Utility method to reset the counter (mimicking a static reset method in User class)
    private void resetUserCounter() {
        try {
            java.lang.reflect.Field counterField = User.class.getDeclaredField("counter");
            counterField.setAccessible(true);
            counterField.set(null, 0);
            System.out.println("User counter reset to 0.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to reset User counter: " + e.getMessage());
        }
    }
}
