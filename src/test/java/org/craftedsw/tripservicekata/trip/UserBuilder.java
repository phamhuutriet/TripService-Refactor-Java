package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserBuilder {

    private List<User> friends = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder friendWith(User... users) {
        friends.addAll(Arrays.asList(users));
        return this;
    }

    public UserBuilder withTrips(Trip... new_trips) {
        trips.addAll(Arrays.asList(new_trips));
        return this;
    }

    public User build() {
        User newUser = new User();
        addFriends(newUser);
        addTrips(newUser);
        return newUser;
    }

    public void addFriends(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }

    public void addTrips(User user) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }
}
