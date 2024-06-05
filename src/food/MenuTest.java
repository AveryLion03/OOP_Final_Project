package food;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MenuTest {

    @Test
    public void testAddDish() {
        Menu menu = new Menu();
        Dishes dish = new Dishes("Pizza", "Main", "Standard", 10.0);
        menu.addDish(dish);
        assertTrue(menu.getAvailDishes().contains(dish));
    }

    @Test
    public void testAddMeal() {
        Menu menu = new Menu();
        Meal meal = new Meal("Combo Meal", 20.0, false);
        menu.addMeal(meal);
        assertTrue(menu.getAvailMeals().contains(meal));
    }

    

    @Test
    public void testAddSpecial() {
        Menu menu = new Menu();
        Meal meal = new Meal("Special Meal", 20.0, false);
        menu.addMeal(meal);
        menu.addSpecial(meal, 15.0);
        assertTrue(menu.specials.contains(meal));
        assertFalse(menu.getAvailMeals().contains(meal));
    }

    @Test
    public void testRemoveSpecial() {
        Menu menu = new Menu();
        Meal meal = new Meal("Special Meal", 20.0, true);
        menu.addSpecial(meal, 15.0);
        menu.removeSpecial(meal, 15.0);
        assertFalse(menu.specials.contains(meal));
        assertTrue(menu.getAvailMeals().contains(meal));
    }

    @Test
    public void testUpdateSpecialPrice() {
        Menu menu = new Menu();
        Meal meal1 = new Meal("Special Meal 1", 20.0, true);
        Meal meal2 = new Meal("Special Meal 2", 30.0, true);
        menu.addSpecial(meal1, 15.0);
        menu.addSpecial(meal2, 15.0);
        menu.updateSpecialPrice(20.0, 15.0);
        assertEquals(20.0, meal1.discount_rate);
        assertEquals(20.0, meal2.discount_rate);
    }

    

    @Test
    public void testToString() {
        Menu menu = new Menu();
        Dishes dish = new Dishes("Pizza", "Main", "Standard", 10.0);
        Meal meal = new Meal("Combo Meal", 20.0, false);
        menu.addDish(dish);
        menu.addMeal(meal);

        String expected = "***** Menu: *****\n" +
                          "*     Dishes     *\n" +
                          dish.toString() + "\n" +
                          "*     Meals     *\n" +
                          meal.toString() + "\n" +
                          "*    Specials     *\n";
        assertEquals(expected, menu.toString());
    }
}
