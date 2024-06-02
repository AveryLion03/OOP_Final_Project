package food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;
    private Dishes starter;
    private Dishes main;
    private Dishes dessert;
    private Meal meal;
    private Meal specialMeal;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        starter = new Dishes("Salad", "Starter", "Vegetarian", 5.0);
        main = new Dishes("Steak", "Main", "Non-Vegetarian", 15.0);
        dessert = new Dishes("Cake", "Dessert", "Vegetarian", 7.0);
        meal = new Meal("Special Meal", 20.0, false);
        specialMeal = new Meal("Special Meal", 20.0, true);

        menu.addDish(starter);
        menu.addDish(main);
        menu.addDish(dessert);
        menu.addMeal(meal);
    }

    @Test
    void testAddDish() {
        System.out.println("Running testAddDish...");
        assertTrue(menu.getAvailDishes().contains(starter));
        System.out.println("Dishes: " + menu.getAvailDishes());
    }

    @Test
    void testAddMeal() {
        System.out.println("Running testAddMeal...");
        assertTrue(menu.getAvailMeals().contains(meal));
        System.out.println("Meals: " + menu.getAvailMeals());
    }

    @Test
    void testAddDishToMeal() {
        System.out.println("Running testAddDishToMeal...");
        menu.addDishToMeal(meal, starter);
        menu.addDishToMeal(meal, main);
        menu.addDishToMeal(meal, dessert);
        Meal updatedMeal = menu.getAvailMeals().get(0);
        assertTrue(updatedMeal.toString().contains("Starter: Salad"));
        assertTrue(updatedMeal.toString().contains("Main Course: Steak"));
        assertTrue(updatedMeal.toString().contains("Dessert: Cake"));
        System.out.println("Meal after adding dishes: " + updatedMeal);
    }

    @Test
    void testAddDishToNonexistentMeal() {
        System.out.println("Running testAddDishToNonexistentMeal...");
        Meal nonExistentMeal = new Meal("Nonexistent Meal");
        menu.addDishToMeal(nonExistentMeal, starter);
        for (Meal m : menu.getAvailMeals()) {
            assertFalse(m.toString().contains("Nonexistent Meal"));
        }
        System.out.println("Nonexistent meal was not modified.");
    }

    @Test
    void testAddSpecial() {
        System.out.println("Running testAddSpecial...");
        menu.addSpecial(specialMeal, 10.0);
        assertTrue(menu.specials.contains(specialMeal));
        assertEquals(10.0, specialMeal.discount_rate);
        System.out.println("Specials: " + menu.specials);
    }

    @Test
    void testRemoveSpecial() {
        System.out.println("Running testRemoveSpecial...");
        menu.addSpecial(specialMeal, 10.0);
        menu.removeSpecial(specialMeal, 10.0);
        assertFalse(menu.specials.contains(specialMeal));
        System.out.println("Specials after removal: " + menu.specials);
    }

    @Test
    void testUpdateSpecialPrice() {
        System.out.println("Running testUpdateSpecialPrice...");
        menu.addSpecial(specialMeal, 10.0);
        menu.updateSpecialPrice(20.0, 10.0);
        assertEquals(20.0, specialMeal.discount_rate);
        System.out.println("Updated specials: " + menu.specials);
    }

    @Test
    void testShowMeal() {
        System.out.println("Running testShowMeal...");
        menu.showMeal("Special Meal");
        menu.showMeal("Non-existent Meal");
    }

    @Test
    void testToString() {
        System.out.println("Running testToString...");
        String menuString = menu.toString();
        assertNotNull(menuString);
        assertTrue(menuString.contains("Menu: \n"));
        assertTrue(menuString.contains("Dishes: \n"));
        assertTrue(menuString.contains("Meals: \n"));
        System.out.println("Menu toString: " + menuString);
    }
}
