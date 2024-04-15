package org.example.ub3.two;

import org.example.coll.MyBidirectionalDictionary;
import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.ub1.three.app.Gender;
import org.example.coll.tuple.Tuple;
import org.example.ub3.twov2.Divorce;

import java.time.LocalDate;

public class MarriageBureau {
    private final MyBidirectionalDictionary<Person, Person> _marriages;
    private final MyDictionary<Person, String> _originalSurnames;
    private final MyDictionary<Person, MyCollection<Person>> _bridesmen;
    private final MyDictionary<Person, MyCollection<Person>> _bridesmaids;
    private final MyDictionary<Tuple<Person, Person>, Divorce> _divorces;

     MarriageBureau() {
        _marriages = new MyBidirectionalDictionary<>();
        _originalSurnames = new MyDictionary<>();
        _bridesmen = new MyDictionary<>();
        _bridesmaids = new MyDictionary<>();
        _divorces = new MyDictionary<>();
    }

    private MyCollection<Person> getMarriedPersons() {
        MyCollection<Person> result = _marriages.getKeys();
        result.add(_marriages.getValues());
        return result;
    }

    public void marry(Person p1, Person p2) {
         this.marry(p1, p2, new MyCollection<>(), new MyCollection<>());
    }

    public void marry(Person p1, Person p2, MyCollection<Person> bridesmen, MyCollection<Person> bridesmaids) {
        MyCollection<Person> marriedPersons = getMarriedPersons();
        if (marriedPersons.contains(p1) || marriedPersons.contains(p2)) {
            throw new IllegalArgumentException("Person already married");
        }
        if (p1.GENDER == p2.GENDER) {
            throw new IllegalArgumentException("Both persons are of the same gender");
        }
        if (p1.GENDER == Gender.FEMALE) {
            Person temp = p1;
            p1 = p2;
            p2 = temp;
        }
        _bridesmen.put(p1, bridesmen);
        _bridesmaids.put(p2, bridesmaids);
        _originalSurnames.put(p2, p2.getSurname());
        p2.setSurname(p1.getSurname());
        _marriages.put(p1, p2);
    }

    public void divorce(Person p, String reason) {
        if (!getMarriedPersons().contains(p)) {
            throw new IllegalArgumentException("Person is not married");
        }
        Person female = _marriages.get(p);
        if (p.GENDER == Gender.FEMALE) {
            Person temp = p;
            p = female;
            female = temp;
        }
        _divorces.put(new Tuple<>(p, female), new Divorce(LocalDate.now(), reason));
        _marriages.remove(p);
        _bridesmen.remove(p);
        _bridesmaids.remove(female);
        female.setSurname(_originalSurnames.get(female));
    }
}
