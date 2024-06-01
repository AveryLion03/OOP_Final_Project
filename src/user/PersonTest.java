package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        // Reset the counter before each test to ensure consistent IDs
        resetUserCounter();
        person = new Person("johndoe", "password123", "Customer", "John", "Doe");
    }

    @Test
    void testPersonCreation() {
        System.out.println("Running testPersonCreation...");
        assertEquals(1, person.getID());
        System.out.println("Person ID: " + person.getID());
        assertEquals("johndoe", person.getUsername());
        System.out.println("Username: " + person.getUsername());
        assertEquals("password123", person.getPassword());
        System.out.println("Password: " + person.getPassword());
        assertEquals("Customer", person.getUserType());
        System.out.println("User Type: " + person.getUserType());
        assertEquals("John", person.getName());
        System.out.println("Name: " + person.getName());
        assertEquals("Doe", person.getSurname());
        System.out.println("Surname: " + person.getSurname());
    }

    @Test
    void testSetName() {
        System.out.println("Running testSetName...");
        person.setName("Jane");
        assertEquals("Jane", person.getName());
        System.out.println("Updated Name: " + person.getName());
    }

    @Test
    void testSetSurname() {
        System.out.println("Running testSetSurname...");
        person.setSurname("Smith");
        assertEquals("Smith", person.getSurname());
        System.out.println("Updated Surname: " + person.getSurname());
    }

    @Test
    void testGetFullName() {
        System.out.println("Running testGetFullName...");
        assertEquals("John Doe", person.getFullName());
        System.out.println("Full Name: " + person.getFullName());
        person.setName("Jane");
        person.setSurname("Smith");
        assertEquals("Jane Smith", person.getFullName());
        System.out.println("Updated Full Name: " + person.getFullName());
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
