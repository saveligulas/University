package hum;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final int _id;
    private final NameOfPerson _name;
    private LendingProfile _profile;
    private final List<Integer> _futureReservationIds;
    private final List<Integer> _presentReservationIds;
    private final List<Integer> _pastReservationIds;

    public Customer(int _id, NameOfPerson _name, LendingProfile _profile, List<Integer> _futureReservationIds, List<Integer> _presentReservationIds, List<Integer> _pastReservationIds) {
        this._id = _id;
        this._name = _name;
        this._profile = _profile;
        this._futureReservationIds = _futureReservationIds;
        this._presentReservationIds = _presentReservationIds;
        this._pastReservationIds = _pastReservationIds;
    }

    public Customer(int _id, NameOfPerson _name, LendingProfile _profile) {
        this(_id, _name, _profile, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Customer(int _id, NameOfPerson _name) {
        this(_id, _name, LendingProfile.EXTERNAL);
    }

}
