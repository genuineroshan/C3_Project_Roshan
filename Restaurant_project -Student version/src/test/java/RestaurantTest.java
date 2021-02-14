import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
    Restaurant restaurant;

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurant = MockedData.ArrangeRestaurant();
        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.doReturn(LocalTime.parse("14:30:00")).when(restaurantSpy).getCurrentTime();
        assertTrue(restaurantSpy.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurant = MockedData.ArrangeRestaurant();
        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.doReturn(LocalTime.parse("23:00:00")).when(restaurantSpy).getCurrentTime();
        assertFalse(restaurantSpy.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        this.restaurant = MockedData.ArrangeRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        this.restaurant = MockedData.ArrangeRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        this.restaurant = MockedData.ArrangeRestaurant();
        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void order_total_cost_should_be_calculated_on_selecting_menu_items(){
        this.restaurant = MockedData.ArrangeRestaurant();
        ArrayList<String> selectedItems = new ArrayList<>();
        selectedItems.add("Vegetable lasagne");
        int expectedTotalCost = 0;
        for (Item menuItem : this.restaurant.getMenu()) {
            for (String selectedItem : selectedItems) {
                if(selectedItem.equals(menuItem.getName())){
                    expectedTotalCost+=menuItem.getPrice();
                }
            }
        }

        int actualTotalCost = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL>>>>>>>>>>>>>>>>>>>>>>
}