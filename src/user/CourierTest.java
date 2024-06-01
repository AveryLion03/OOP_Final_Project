package user;

import Code.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {

    private Courier courier;
    private Location location;

    @BeforeEach
    void setUp() {
        // Reset the counter before each test to ensure consistent IDs
        resetUserCounter();
        location = new Location(40.7128, -74.0060);
        courier = new Courier("courier_john", "pass123", "Courier", "John", "Doe", "123456789", 10, true, location);
    }

    @Test
    void testCourierCreation() {
        System.out.println("Running testCourierCreation...");
        assertEquals(1, courier.getID());
        System.out.println("Courier ID: " + courier.getID());
        assertEquals("courier_john", courier.getUsername());
        System.out.println("Username: " + courier.getUsername());
        assertEquals("pass123", courier.getPassword());
        System.out.println("Password: " + courier.getPassword());
        assertEquals("Courier", courier.getUserType());
        System.out.println("User Type: " + courier.getUserType());
        assertEquals("John", courier.getName());
        System.out.println("Name: " + courier.getName());
        assertEquals("Doe", courier.getSurname());
        System.out.println("Surname: " + courier.getSurname());
        assertEquals("123456789", courier.getPhoneNumber());
        System.out.println("Phone Number: " + courier.getPhoneNumber());
        assertEquals(10, courier.getOrderCount());
        System.out.println("Order Count: " + courier.getOrderCount());
        assertTrue(courier.isOnDuty());
        System.out.println("On Duty: " + courier.isOnDuty());
        assertEquals(location, courier.getLoc());
        System.out.println("Location: " + courier.getLoc());
    }

    @Test
    void testSetPhoneNumber() {
        System.out.println("Running testSetPhoneNumber...");
        courier.setPhoneNumber("987654321");
        assertEquals("987654321", courier.getPhoneNumber());
        System.out.println("Updated Phone Number: " + courier.getPhoneNumber());
    }

    @Test
    void testSetOnDuty() {
        System.out.println("Running testSetOnDuty...");
        courier.setOnDuty(false);
        assertFalse(courier.isOnDuty());
        System.out.println("Updated On Duty: " + courier.isOnDuty());
    }

    @Test
    void testSetLoc() {
        System.out.println("Running testSetLoc...");
        Location newLocation = new Location(34.0522, -118.2437);
        courier.setLoc(newLocation);
        assertEquals(newLocation, courier.getLoc());
        System.out.println("Updated Location: " + courier.getLoc());
    }

 

    @Test
    void testToString() {
        System.out.println("Running testToString...");
        String expectedToString = "Courier John Doe: 10";
        assertEquals(expectedToString, courier.toString());
        System.out.println("ToString: " + courier.toString());
    }

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
