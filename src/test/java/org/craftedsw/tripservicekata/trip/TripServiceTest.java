package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class TripServiceTest {

    @Test void
    should_throw_an_exception_when_user_is_not_logged_in() {
        TripService tripService = new TripService();
        Assertions.assertThrows(UserNotLoggedInException.class, (Executable) tripService.getTripsByUser(null));
    }
}
