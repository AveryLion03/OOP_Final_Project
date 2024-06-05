package user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Code.*;
import food.*;


public class CustomerTest {

    @Test
    public void testGetEmail() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    public void testSetEmail() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        customer.setEmail("new.email@example.com");
        assertEquals("new.email@example.com", customer.getEmail());
    }

    @Test
    public void testGetCellNumber() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertEquals("123-456-7890", customer.getCellNumber());
    }

    @Test
    public void testSetCellNumber() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        customer.setCellNumber("098-765-4321");
        assertEquals("098-765-4321", customer.getCellNumber());
    }

    @Test
    public void testGetLoc() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertEquals(location, customer.getLoc());
    }

    @Test
    public void testSetLoc() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        Location newLocation = new Location(30.0, 40.0);
        customer.setLoc(newLocation);
        assertEquals(newLocation, customer.getLoc());
    }

    @Test
    public void testGetFidelity() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertEquals("Basic", customer.getFidelity());
    }

    @Test
    public void testSetFidelity() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertTrue(customer.setFidelity("Point"));
        assertEquals("Point", customer.getFidelity());
    }

    @Test
    public void testInvalidFidelity() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertFalse(customer.setFidelity("Invalid"));
        assertEquals("Basic", customer.getFidelity());
    }

    @Test
    public void testGetPoints() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertEquals(0, customer.getPoints());
    }

    @Test
    public void testSetPoints() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        customer.setPoints(50);
        assertEquals(50, customer.getPoints());
    }

    @Test
    public void testPointsDiscount() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        customer.setFidelity("Point");
        assertFalse(customer.pointsDiscount(50));
        assertEquals(50, customer.getPoints());
        assertTrue(customer.pointsDiscount(50));
        assertEquals(100, customer.getPoints());
    }

    @Test
    public void testGetActiveOrder() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertNull(customer.getActiveOrder());
    }

    @Test
    public void testSetActiveOrder() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Order order = new Order("Order1", restaurant);
        customer.setActiveOrder(order);
        assertEquals(order, customer.getActiveOrder());
    }

    @Test
    public void testIsFreeOrder() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        assertFalse(customer.isFreeOrder());
    }

    @Test
    public void testSetFreeOrder() {
        Location location = new Location(10.0, 20.0);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", location);
        customer.setFreeOrder(true);
        assertTrue(customer.isFreeOrder());
    }
}
