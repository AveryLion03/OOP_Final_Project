package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Reset the counter before each test to ensure consistent IDs
        resetUserCounter();
        user = new User("john_doe", "password123", "Customer", "John Doe");
    }

    @Test
    void testUserCreation() {
        System.out.println("Running testUserCreation...");
        assertEquals(1, user.getID());
        System.out.println("User ID: " + user.getID());
        assertEquals("john_doe", user.getUsername());
        System.out.println("Username: " + user.getUsername());
        assertEquals("password123", user.getPassword());
        System.out.println("Password: " + user.getPassword());
        assertEquals("Customer", user.getUserType());
        System.out.println("User Type: " + user.getUserType());
        assertEquals("John Doe", user.getName());
        System.out.println("Name: " + user.getName());
    }

    @Test
    void testSetUsername() {
        System.out.println("Running testSetUsername...");
        user.setUsername("jane_doe");
        assertEquals("jane_doe", user.getUsername());
        System.out.println("Updated Username: " + user.getUsername());
    }

    @Test
    void testSetPassword() {
        System.out.println("Running testSetPassword...");
        user.setPassword("newpassword123");
        assertEquals("newpassword123", user.getPassword());
        System.out.println("Updated Password: " + user.getPassword());
    }

    @Test
    void testSetUserType() {
        System.out.println("Running testSetUserType...");
        user.setUserType("Manager");
        assertEquals("Manager", user.getUserType());
        System.out.println("Updated User Type: " + user.getUserType());
    }

    @Test
    void testSetName() {
        System.out.println("Running testSetName...");
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
        System.out.println("Updated Name: " + user.getName());
    }

    @Test
    void testGetID() {
        System.out.println("Running testGetID...");
        assertEquals(1, user.getID());
        System.out.println("User ID: " + user.getID());
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
