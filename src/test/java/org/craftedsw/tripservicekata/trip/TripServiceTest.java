package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.springframework.util.Assert;

import java.util.List;

public class TripServiceTest {

    private User loggedUser;
    final private User GUEST = null;
    final private User LOGGED_USER = new User();

    @Test void
    should_throw_an_exception_when_user_is_not_logged_in() {
        TestableTripService tripService = new TestableTripService();

        loggedUser = GUEST;

        Assertions.assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(loggedUser));
    }

    @Test void
    should_return_empty_list_if_user_is_not_friend() {
        TestableTripService tripService = new TestableTripService();

        loggedUser = LOGGED_USER;

        Assertions.assertEquals(tripService.getTripsByUser(loggedUser).size(), 0);
    }

    @Test void
    should_return_trip_list_if_user_is_friend() {
        TestableTripService tripService = new TestableTripService();

        loggedUser = LOGGED_USER;
        User friend = new User();
        friend.addFriend(loggedUser);

        friend.addTrip(new Trip());
        friend.addTrip(new Trip());
        friend.addTrip(new Trip());
        friend.addTrip(new Trip());

        Assertions.assertEquals(tripService.getTripsByUser(friend).size(), 4);

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
