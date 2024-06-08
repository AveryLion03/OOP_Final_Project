package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import food.Dishes;
import food.Meal;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {
    private Meal meal;
    private Dishes starter;
    private Dishes main;
    private Dishes dessert;

    @BeforeEach
    void setUp() {
        meal = new Meal("Special Meal", 20.0, false);
        starter = new Dishes("Salad", "Starter", "Vegetarian", 5.0);
        main = new Dishes("Steak", "Main", "Non-Vegetarian", 15.0);
        dessert = new Dishes("Cake", "Dessert", "Vegetarian", 7.0);
    }

    @Test
    void testMealCreation() {
        System.out.println("Running testMealCreation...");
        assertEquals("SPECIAL MEAL", meal.getMealName());
        System.out.println("Meal Name: " + meal.getMealName());
        assertEquals(20.0, meal.getPrice());
        System.out.println("Price: " + meal.getPrice());
        assertFalse(meal.getDeal());
        System.out.println("Deal: " + meal.getDeal());
    }

    @Test
    void testAddDishes() {
        System.out.println("Running testAddDishes...");
        double initialPrice = meal.getPrice();
        meal.addDishes(starter);
        meal.addDishes(main);
        meal.addDishes(dessert);
        double expectedPrice = initialPrice +
            (starter.getUnitPrice() * 0.95) +
            (main.getUnitPrice() * 0.95) +
            (dessert.getUnitPrice() * 0.95);
        assertEquals(expectedPrice, meal.getPrice(), 0.01);
        System.out.println("Updated Price: " + meal.getPrice());
    }

    @Test
    void testApplyDiscount() {
        System.out.println("Running testApplyDiscount...");
        meal.applyDiscount(20.0);
        assertTrue(meal.getDeal());
        System.out.println("Deal: " + meal.getDeal());
        assertEquals(20.0, meal.getDiscount());
        System.out.println("Discount Rate: " + meal.getDiscount());
    }

    @Test
    void testRemoveDiscount() {
        System.out.println("Running testRemoveDiscount...");
        meal.applyDiscount(20.0);
        meal.removeDiscount(20.0);
        assertFalse(meal.getDeal());
        System.out.println("Deal: " + meal.getDeal());
        assertEquals(0.95 * meal.getPrice() / (1.0 - 1.0 / 20.0), meal.getPrice(), 0.01);
        System.out.println("Price after removing discount: " + meal.getPrice());
    }

    @Test
    void testMakeMeal() {
        System.out.println("Running testMakeMeal...");
        meal.makeMeal("half");
        meal.makeMeal("full");
        meal.makeMeal("invalid");
    }

    @Test
    void testEquals() {
        System.out.println("Running testEquals...");
        Meal anotherMeal = new Meal("Special Meal", 20.0, false);
        assertEquals(meal, anotherMeal);
        System.out.println("Meals are equal: " + meal.equals(anotherMeal));

        Meal differentMeal = new Meal("Different Meal", 25.0, false);
        assertNotEquals(meal, differentMeal);
        System.out.println("Meals are not equal: " + meal.equals(differentMeal));
    }

    @Test
    void testToString() {
        System.out.println("Running testToString...");
        meal.addDishes(starter);
        meal.addDishes(main);
        meal.addDishes(dessert);
        String expectedString = "Meal Name: SPECIAL MEAL\n" +
        		"FULL_MEAL\n" +
                "Starter: Salad\n" +
                "Main Course: Steak\n" +
                "Dessert: Cake\n" +
                "Price: $" + meal.getPrice() + "\n" +
                "Special: No\n";
        assertEquals(expectedString, meal.toString());
        System.out.println("Meal toString: " + meal.toString());
    }
}
