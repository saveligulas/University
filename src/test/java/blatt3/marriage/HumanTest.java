package blatt3.marriage;

import org.example.ub1.three.app.Gender;
import org.example.ub3.twov2.Human;
import org.example.ub3.twov2.Name;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanTest {
    @Test
    public void testEquality() {
        Human human1 = new Human(Gender.MALE, LocalDate.of(1990, 5, 15), new Name("John", null, "Doe"));
        Human human2 = new Human(Gender.MALE, LocalDate.of(1990, 5, 15), new Name("John", null, "Doe"));
        Human human3 = new Human(Gender.FEMALE, LocalDate.of(1995, 3, 20), new Name("Alice", null, "Smith"));

        assertTrue(human1.equals(human2));
        assertFalse(human1.equals(human3));
    }

    @Test
    public void testProposal() {
        Human human1 = new Human(Gender.MALE);
        Human human2 = new Human(Gender.FEMALE);

        assertTrue(human1.propose(human2));
        assertFalse(human1.propose(human1)); // Can't propose to self
    }

    @Test
    public void testAcceptProposal() {
        Human human1 = new Human(Gender.MALE);
        Human human2 = new Human(Gender.FEMALE);
        human1.propose(human2);

        assertTrue(human2.acceptProposal());
    }

}
