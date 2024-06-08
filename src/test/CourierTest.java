package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Code.*;
import food.*;
import user.Courier;
import user.Location;
import user.Restaurant;


public class CourierTest {

    @Test
    public void testGetPhoneNumber() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        assertEquals("123-456-7890", courier.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        courier.setPhoneNumber("098-765-4321");
        assertEquals("098-765-4321", courier.getPhoneNumber());
    }

    @Test
    public void testIsOnDuty() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        assertTrue(courier.isOnDuty());
    }

    @Test
    public void testSetOnDuty() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        courier.setOnDuty(false);
        assertFalse(courier.isOnDuty());
    }

    @Test
    public void testGetLoc() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        assertEquals(location, courier.getLoc());
    }

    

   

    @Test
    public void testGetOrderCount() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        assertEquals(0, courier.getOrderCount());
    }

    @Test
    public void testSetOrder() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 0, true, location);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Order order = new Order("Order1", restaurant);
        courier.setOrder(order);
        assertEquals(1, courier.getOrderCount());
        assertTrue(courier.getJobs().contains(order));
    }

    

    @Test
    public void testToString() {
        Location location = new Location(10.0, 20.0);
        Courier courier = new Courier("courierUser", "password", "Courier", "Jane", "Doe", "123-456-7890", 5, true, location);
        String expected = "Courier Jane Doe: 5";
        assertEquals(expected, courier.toString());
    }

 
}
