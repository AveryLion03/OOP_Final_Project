package food;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Commands.*;
import Code.*;
import user.*;
import food.*;




public class OrderTest {

    @Test
    public void testAddMeal() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        Meal meal = new Meal("Meal1");
        meal.setPrice(10.0);

        order.addMeal(meal);

        assertEquals(1, order.meals.size());
        assertEquals(10.0, order.getProfit(), 0.01);
    }

    @Test
    public void testAddDish() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        Dishes dish = new Dishes("Dish1", "main", "standard", 5.0);

        order.addDish(dish);

        assertEquals(1, order.dish.size());
        assertEquals(5.0, order.getProfit(), 0.01);
    }

    @Test
    public void testSetCompleted() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        order.setCompleted(true);
        assertTrue(order.completedDelivery);
    }

    @Test
    public void testSetDate() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        order.setDate("01/06/2024");
        assertEquals("01/06/2024", order.date);
    }

    @Test
    public void testSetCustomer() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        Customer customer = new Customer("customerUser", "password", "Customer", "John", "Doe", "john.doe@example.com", "123-456-7890", new Location(0.0, 0.0));
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    public void testGetOrderName() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        assertEquals("Order1", order.getOrderName());
    }

    @Test
    public void testGetRestaurant() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        assertEquals(restaurant, order.getRestaurant());
    }

    @Test
    public void testAdd2Order() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        Meal meal = new Meal("Meal1");
        Dishes dish = new Dishes("Dish1", "main", "standard", 5.0);
        meal.setPrice(10.0);

        order.add2Order(meal, dish);

        assertEquals(1, order.meals.size());
        assertEquals(1, order.dish.size());
        assertEquals(15.0, order.getProfit(), 0.01);
    }

    @Test
    public void testToString() {
        Restaurant restaurant = new Restaurant("restaurantUser", "password", "Restaurant", "Test Restaurant", new Location(0.0, 0.0));
        Order order = new Order("Order1", restaurant);
        order.setDate("01/06/2024");
        Meal meal = new Meal("Meal1");
        meal.setPrice(15.0);
        order.addMeal(meal);

        String expected = "- Order Name: Order1\n" +
                          "- Number of Items Ordered: 1\n" +
                          "- Total Price: $15,00\n" +
                          "- Date Ordered: 01/06/2024\n";
        assertEquals(expected, order.toString());
    }
}
