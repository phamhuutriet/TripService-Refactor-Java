package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;

public class TripServiceTest {

    private User loggedUser;
    final private User GUEST = null;
    private User LOGGED_USER;
    final private User TRIET = new User();
    final private User MAI = new User();
    final private Trip TO_VN = new Trip();
    final private Trip TO_CANADA = new Trip();
    final private Trip TO_USA = new Trip();
    private TestableTripService tripService;

    @BeforeEach
    public void initialize() {
        tripService = new TestableTripService();
        LOGGED_USER = new User();
    }

    @Test void
    should_throw_an_exception_when_user_is_not_logged_in() {
        Assertions.assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(GUEST, null));
    }

    @Test void
    should_return_empty_list_if_user_is_not_friend() {
        User friend = aUser().friendWith(TRIET).withTrips(TO_CANADA).build();

        Assertions.assertEquals(0, tripService.getTripsByUser(friend, LOGGED_USER).size());
    }

    @Test void
    should_return_trip_list_if_user_is_friend() {
        User friend = aUser().friendWith(LOGGED_USER).withTrips(TO_CANADA, TO_USA, TO_VN).build();

        Assertions.assertEquals(3, tripService.getTripsByUser(friend, LOGGED_USER).size());

    }

    class TestableTripService extends TripService{
        @Override
        public User getLoggedUser() {
            return loggedUser;
        }

        @Override
        public List<Trip> find_trip_by_user(User user) {
            return user.trips();
        }
    }
}

