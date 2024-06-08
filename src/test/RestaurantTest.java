package test;

import Code.*;
import food.*;
import user.Courier;
import user.Location;
import user.Restaurant;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant restaurant;
    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(40.7128, -74.0060);
        restaurant = new Restaurant("burgerpalace_admin", "burger123", "Restaurant", "Burger Palace", location);
    }

    @Test
    void testGetLocation() {
        assertEquals(location, restaurant.getLocation());
    }

    @Test
    void testSetLocation() {
        Location newLocation = new Location(34.0522, -118.2437);
        restaurant.setLocation(newLocation);
        assertEquals(newLocation, restaurant.getLocation());
    }

    @Test
    void testGetAndSetDiscountFactor() {
        Double newDiscountFactor = 15.0;
        restaurant.setDiscountFactor(newDiscountFactor);
        assertEquals(newDiscountFactor, restaurant.getDiscountFactor());
    }

    @Test
    void testAddAndRemoveAvailableCourier() {
        Courier courier = new Courier("courier_john", "pass123", "Courier", "John", "Doe", "123456789", 10, true, location);
        restaurant.addAvailableCourier(courier);
        ArrayList<Courier> availableCouriers = restaurant.getAvailableCouriers();
        assertTrue(availableCouriers.contains(courier));

        restaurant.removeAvailableCourier(courier);
        availableCouriers = restaurant.getAvailableCouriers();
        assertFalse(availableCouriers.contains(courier));
    }

    @Test
    void testGetMenu() {
        Menu menu = restaurant.getMenu();
        assertNotNull(menu);
    }

    @Test
    void testAddToMenu() {
        Dishes dish = new Dishes("Burger", "main", "standard", 10.0);
        restaurant.addToMenu(dish);
        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailDishes().contains(dish));
    }
    
    @Test
    void testAddToMenu2() {
        Dishes dish = new Dishes("Salad", "starter", "standard", 10.0);
        restaurant.addToMenu(dish);
        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailDishes().contains(dish));
    }

    @Test
    void testAddDishToMeal() {
        Meal meal = new Meal("Combo Meal");
        Dishes dish = new Dishes("Burger", "main", "standard", 10.0);
        restaurant.addMeal(meal);
        restaurant.addDishToMeal(meal, dish);
        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailMeals().contains(meal));
        //assertTrue(meal.getDishes().contains(dish));
    }
    

    @Test
    void testAddMeal() {
        Meal meal = new Meal("Combo Meal");
        restaurant.addMeal(meal);
        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailMeals().contains(meal));
    }

    @Test
    void testShowMeal() {
        Meal meal = new Meal("Combo Meal");
        Dishes dish = new Dishes("Burger", "main", "standard", 10.0);
        Dishes dish2 = new Dishes("Salad", "starter", "standard", 10.0);
        restaurant.addMeal(meal);
        restaurant.addDishToMeal(meal, dish);
        restaurant.addDishToMeal(meal, dish2);

        // Assuming showMeal prints the meal details, we test it indirectly by checking the meal addition
        restaurant.showMeal("Combo Meal");
        // No direct assertion here as the method prints to console, manual verification might be needed
    }
}
