import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// this test class is created specifically for testing GetTotalCostOrder() method
// in order to see if it returns correct total price after selecting available menu
// and to ensure that this method will throw the correct exception if it fails

public class GetTotalCostOrderTest {

    @Mock
    Restaurant restaurant;

    @Mock
    RestaurantService service;

    // Refactoring code

    @BeforeEach
    public void init() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        service = new RestaurantService();
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Shrimp salad", 199);
        restaurant.addToMenu("Chinese noodles", 50);
    }

    @Test
    public void test_getMenu_not_null() {

        assertNotNull(restaurant.getMenu());

    }

    @Test
    public void test_return_correct_getTotalPrice() {

        assertEquals(119+269+199+50, restaurant.getTotalPrice());

    }

    @Test
    public void test_return_correct_totalPrice_with_selected_item_names() throws itemNotFoundException {

        assertEquals(119+199, restaurant.getTotalCostOrder("Sweet corn soup", "Shrimp salad"));
        assertEquals(119+50, restaurant.getTotalCostOrder("Sweet corn soup", "Chinese noodles"));
        assertEquals(199+269, restaurant.getTotalCostOrder("Shrimp salad", "Vegetable lasagne"));
        assertEquals(199+269+50, restaurant.getTotalCostOrder("Shrimp salad", "Vegetable lasagne", "Chinese noodles"));

        String[] items = {"Shrimp salad", "Vegetable lasagne", "Chinese noodles"};
        assertEquals(199+269+50, restaurant.getTotalCostOrder(items));

    }

    @Test
    public void test_getTotalCostOrder_throws_itemNotFoundException() {

        assertThrows(itemNotFoundException.class, () -> restaurant.getTotalCostOrder("Shrimp salad", "Vegetable lasagne", "Thai noodles"));
        assertThrows(itemNotFoundException.class, () -> restaurant.getTotalCostOrder("Pizza", "Chinese noodles"));

    }
}