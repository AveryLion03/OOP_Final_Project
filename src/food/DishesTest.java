package food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishesTest {
    private Dishes dish;

    @BeforeEach
    void setUp() {
        dish = new Dishes("Pasta", "Vegetarian", "Main", 12.99);
    }

    @Test
    void testDishCreation() {
        System.out.println("Running testDishCreation...");
        assertEquals("Pasta", dish.getDishName());
        System.out.println("Dish Name: " + dish.getDishName());
        assertEquals("Vegetarian", dish.getDishCategory());
        System.out.println("Dish Category: " + dish.getDishCategory());
        assertEquals("Main", dish.getDishType());
        System.out.println("Dish Type: " + dish.getDishType());
        assertEquals(12.99, dish.getUnitPrice());
        System.out.println("Unit Price: " + dish.getUnitPrice());
    }

    @Test
    void testSetDishName() {
        System.out.println("Running testSetDishName...");
        dish.setDishName("Salad");
        assertEquals("Salad", dish.getDishName());
        System.out.println("Updated Dish Name: " + dish.getDishName());
    }

    @Test
    void testSetUnitPrice() {
        System.out.println("Running testSetUnitPrice...");
        dish.setUnitPrice(9.99);
        assertEquals(9.99, dish.getUnitPrice());
        System.out.println("Updated Unit Price: " + dish.getUnitPrice());
    }

    @Test
    void testIsGlutenFree() {
        System.out.println("Running testIsGlutenFree...");
        Dishes glutenFreeDish = new Dishes("Rice", "Gluten-Free", "Side", 5.99);
        assertTrue(glutenFreeDish.isGlutenFree());
        System.out.println("Dish is Gluten-Free: " + glutenFreeDish.isGlutenFree());

        Dishes nonGlutenFreeDish = new Dishes("Bread", "Non Gluten-Free", "Side", 2.99);
        assertFalse(nonGlutenFreeDish.isGlutenFree());
        System.out.println("Dish is not Gluten-Free: " + nonGlutenFreeDish.isGlutenFree());
    }

    @Test
    void testIsVeg() {
        System.out.println("Running testIsVeg...");
        assertTrue(dish.isVeg());
        System.out.println("Dish is Vegetarian: " + dish.isVeg());

        Dishes nonVegDish = new Dishes("Chicken", "Non-Vegetarian", "Main", 14.99);
        assertFalse(nonVegDish.isVeg());
        System.out.println("Dish is not Vegetarian: " + nonVegDish.isVeg());
    }

    @Test
    void testToString() {
        System.out.println("Running testToString...");
        String expectedString = "Dish Name: Pasta\n" +
                                "Dish Type: Main\n" +
                                "Dish Category: Vegetarian\n" +
                                "Unit Price: 12.99\n";
        assertEquals(expectedString, dish.toString());
        System.out.println("Dish toString: " + dish.toString());
    }
}
