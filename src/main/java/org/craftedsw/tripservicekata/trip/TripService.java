package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		return isFriend(user, loggedUser) ? find_trip_by_user(user) : noTrips();
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private boolean isFriend(User user, User loggedUser) {
		return user.getFriends().contains(loggedUser);
	}

	public User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

	public List<Trip> find_trip_by_user(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
}
