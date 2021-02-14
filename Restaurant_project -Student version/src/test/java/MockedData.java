import java.time.LocalTime;

public class MockedData {
    /**
     * Arrange the Restaurant object for unit testing.
     */
    public static Restaurant ArrangeRestaurant() {
        return ArrangeRestaurant(null);
    }

    /**
     * Arrange the Restaurant object for unit testing.
     */
    public static Restaurant ArrangeRestaurant(RestaurantService service) {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant restaurant;
        if (service == null) {
            restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        }else {
            restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        }
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 26);
        return restaurant;
    }
}
