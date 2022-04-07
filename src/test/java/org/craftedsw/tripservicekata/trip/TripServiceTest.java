package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;

public class TripServiceTest {

    private User loggedUser;
    final private User GUEST = null;
    final private User LOGGED_USER = new User();
    final private User TRIET = new User();
    final private User MAI = new User();
    final private Trip TO_VN = new Trip();
    final private Trip TO_CANADA = new Trip();
    final private Trip TO_USA = new Trip();
    final private TestableTripService tripService = new TestableTripService();

    @Test void
    should_throw_an_exception_when_user_is_not_logged_in() {
        loggedUser = GUEST;

        Assertions.assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(loggedUser));
    }

    @Test void
    should_return_empty_list_if_user_is_not_friend() {
        User friend = aUser().friendWith(TRIET).withTrips(TO_CANADA).build();
        loggedUser = LOGGED_USER;

        Assertions.assertEquals(tripService.getTripsByUser(friend).size(), 0);
    }

    @Test void
    should_return_trip_list_if_user_is_friend() {
        loggedUser = LOGGED_USER;
        User friend = aUser().friendWith(loggedUser).withTrips(TO_CANADA, TO_USA, TO_VN).build();

        Assertions.assertEquals(tripService.getTripsByUser(friend).size(), 3);

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

