import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {
    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws RestaurantNotFoundException {
        this.restaurant = MockedData.ArrangeRestaurant(service);
        Restaurant actualRestaurant = service.findRestaurantByName("Amelie's cafe");
        assertEquals(actualRestaurant.getName(), this.restaurant.getName());
        assertEquals(actualRestaurant.openingTime, this.restaurant.openingTime);
        assertEquals(actualRestaurant.closingTime, this.restaurant.closingTime);
        assertEquals(actualRestaurant.getLocation(), this.restaurant.getLocation());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws RestaurantNotFoundException {
        MockedData.ArrangeRestaurant(service);
        assertThrows(RestaurantNotFoundException.class, () -> service.findRestaurantByName("Kapor's cafe"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws RestaurantNotFoundException {
        MockedData.ArrangeRestaurant(service);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws RestaurantNotFoundException {
        MockedData.ArrangeRestaurant(service);
        assertThrows(RestaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        MockedData.ArrangeRestaurant(service);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}