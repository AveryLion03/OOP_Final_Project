package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.*;
import Code.*;
import food.Dishes;
import food.Meal;
import food.Order;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    private Meal meal;
    private Dishes starter;
    private Dishes main;
    private Dishes dessert;
    private Restaurant restaurant;
    private Customer customer;
    private Courier courier;

    @BeforeEach
    void setUp() {
        order = new Order("Order1", this.restaurant);
        meal = new Meal("Special Meal", 20.0, false);
        starter = new Dishes("Salad", "Starter", "Vegetarian", 5.0);
        main = new Dishes("Steak", "Main", "Non-Vegetarian", 15.0);
        dessert = new Dishes("Cake", "Dessert", "Vegetarian", 7.0);
        restaurant = new Restaurant("restaurant_admin", "rest123", "Restaurant", "Gourmet Kitchen", new Location(40.7128, -74.0060));
        customer = new Customer("customer_jane", "pass123", "Customer", "Jane", "Doe", "jane.doe@example.com", "555-5678", new Location(34.0522, -118.2437));
        courier = new Courier("john_driver", "password", "Courier", "John", "Doe", "555-1234", 5, true, new Location(34.0522, -118.2437));
    }

    @Test
    void testAddMeal() {
        System.out.println("Running testAddMeal...");
        order.addMeal(meal);
        assertEquals(1, order.getMeals().size());
        assertEquals(meal.getPrice(), order.getProfit(), 0.01);
        System.out.println("Meals: " + order.getMeals());
        System.out.println("Profit after adding meal: $" + order.getProfit());
    }

    @Test
    void testAddDish() {
        System.out.println("Running testAddDish...");
        order.addDish(starter);
        assertEquals(1, order.getDishes().size());
        assertEquals(starter.getUnitPrice(), order.getProfit(), 0.01);
        System.out.println("Dishes: " + order.getDishes());
        System.out.println("Profit after adding dish: $" + order.getProfit());
    }

    @Test
    void testGetProfit() {
        System.out.println("Running testGetProfit...");
        order.addMeal(meal);
        order.addDish(starter);
        assertEquals(meal.getPrice() + starter.getUnitPrice(), order.getProfit(), 0.01);
        System.out.println("Total profit: $" + order.getProfit());
    }

    @Test
    void testSetDate() {
        System.out.println("Running testSetDate...");
        String date = "2024-06-02";
        order.setDate(date);
        assertEquals(date, order.getDate());
        System.out.println("Order date: " + order.getDate());
    }

    @Test
    void testSetRestaurant() {
        System.out.println("Running testSetRestaurant...");
        order.setRestaurant(restaurant);
        assertEquals(restaurant, order.getRestaurant());
        System.out.println("Order restaurant: " + order.getRestaurant().getName());
    }

    @Test
    void testSetCustomer() {
        System.out.println("Running testSetCustomer...");
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
        System.out.println("Order customer: " + order.getCustomer().getName() + " " + order.getCustomer().getSurname());
    }

    @Test
    void testCreateOrder() {
        System.out.println("Running testCreateOrder...");
        order = new Order("Order2", restaurant);
        assertEquals("Order2", order.getOrderName()); // The order name should change
        assertEquals(restaurant, order.getRestaurant("Order1"));
        System.out.println("Order name: " + order.getOrderName());
        System.out.println("Order restaurant: " + order.getRestaurant("Order1").getName());
    }

    @Test
    void testAdd2Order() {
        System.out.println("Running testAdd2Order...");
        order.add2Order(meal, starter);
        assertEquals(1, order.getMeals().size());
        assertEquals(1, order.getDishes().size());
        assertEquals(meal.getPrice() + starter.getUnitPrice(), order.getProfit(), 0.01);
        System.out.println("Meals in order: " + order.getMeals());
        System.out.println("Dishes in order: " + order.getDishes());
        System.out.println("Profit after adding meal and dish: $" + order.getProfit());
    }
    /* Tested before adding systemState into variable for finalizing an order
    @Test
    void testFinalizeOrder() {
        System.out.println("Running testFinalizeOrder...");
        String date = "2024-06-02";
        order.finalizeOrder(date);
        assertEquals(date, order.date);
        System.out.println("Order finalized on: " + order.date);
    }
    */
}
