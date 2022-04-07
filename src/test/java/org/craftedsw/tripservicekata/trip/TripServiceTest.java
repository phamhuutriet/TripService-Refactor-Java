package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.BDDMockito.*;

import java.util.List;

import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;

public class TripServiceTest {

    final private User GUEST = null;
    final private User LOGGED_USER = new User();
    final private User MATH = new User();
    final private Trip TO_VN = new Trip();
    final private Trip TO_CANADA = new Trip();
    final private Trip TO_USA = new Trip();

    @Mock private TripDAO tripDAO;
    @InjectMocks @Spy
    private TripService realTripService = new TripService();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test void
    should_throw_an_exception_when_user_is_not_logged_in() {
        Assertions.assertThrows(UserNotLoggedInException.class, () -> realTripService.getTripsByUser(MATH, GUEST));
    }

    @Test void
    should_return_empty_list_if_user_is_not_friend() {
        User friend = aUser().friendWith(MATH).withTrips(TO_CANADA).build();

        Assertions.assertEquals(0, realTripService.getTripsByUser(friend, LOGGED_USER).size());
    }

    @Test void
    should_return_trip_list_if_user_is_friend() {
        User friend = aUser().friendWith(LOGGED_USER).withTrips(TO_CANADA, TO_USA, TO_VN).build();

        given(tripDAO.getTripsFrom(friend)).willReturn(friend.trips());
        Assertions.assertEquals(3, realTripService.getTripsByUser(friend, LOGGED_USER).size());

    }
}

