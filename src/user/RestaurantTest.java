package user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import food.*;
import Code.*;

public class RestaurantTest {

    @Test
    public void testSetLocation() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);

        Location newLocation = new Location(30.0, 40.0);
        restaurant.setLocation(newLocation);

        assertEquals(newLocation, restaurant.getLocation());
    }

    @Test
    public void testSetDiscountFactor() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);

        restaurant.setDiscountFactor(15.0);

        assertEquals(15.0, restaurant.getDiscountFactor(), 0.01);
    }

    @Test
    public void testAddAvailableCourier() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Courier courier = new Courier("courierUser", "password", "Courier", "John", "Doe", "123-456-7890", 0, false, location);

        restaurant.addAvailableCourier(courier);

        ArrayList<Courier> couriers = restaurant.getAvailableCouriers();
        assertEquals(1, couriers.size());
        assertTrue(couriers.contains(courier));
    }

    @Test
    public void testRemoveAvailableCourier() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Courier courier = new Courier("courierUser", "password", "Courier", "John", "Doe", "123-456-7890", 0, false, location);

        restaurant.addAvailableCourier(courier);
        restaurant.removeAvailableCourier(courier);

        ArrayList<Courier> couriers = restaurant.getAvailableCouriers();
        assertEquals(0, couriers.size());
    }

    @Test
    public void testAddToMenu() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Dishes dish = new Dishes("Dish1", "main", "standard", 5.0);

        restaurant.addToMenu(dish);

        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailDishes().contains(dish));
    }

    @Test
    public void testAddDishToMeal() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Meal meal = new Meal("Meal1");
        Dishes dish = new Dishes("Dish1", "main", "standard", 5.0);

        restaurant.addMeal(meal);
        restaurant.addDishToMeal(meal, dish);

        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailMeals().contains(meal));
        assertTrue(meal.toString().contains("Dish1"));
    }

    @Test
    public void testAddMeal() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Meal meal = new Meal("Meal1");

        restaurant.addMeal(meal);

        Menu menu = restaurant.getMenu();
        assertTrue(menu.getAvailMeals().contains(meal));
    }

    @Test
    public void testShowMeal() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        Meal meal = new Meal("Meal1");

        restaurant.addMeal(meal);
        // Assuming showMeal prints the meal details, we just check the function does not throw an exception
        assertDoesNotThrow(() -> restaurant.showMeal("Meal1"));
    }

    @Test
    public void testCompletedOrders() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);

        restaurant.setCompletedOrders(5);

        assertEquals(5, restaurant.getCompletedOrders());
    }

    @Test
    public void testToString() {
        Location location = new Location(10.0, 20.0);
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", location);
        restaurant.setCompletedOrders(5);

        String expected = "Restaurant Name: Test Restaurant, Completed Orders: 5";
        assertEquals(expected, restaurant.toString());
    }
}
