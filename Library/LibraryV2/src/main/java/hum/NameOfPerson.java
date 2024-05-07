package hum;

import java.util.Collections;
import java.util.List;

public record NameOfPerson(String first, List<String> middle, String last) {
        public static NameOfPerson updateLastName(String newLastName, NameOfPerson name) {
                return new NameOfPerson(name.first, name.middle, newLastName);
        }

        public NameOfPerson(String first, List<String> middle, String last) {
                this.first = first;
                this.middle = Collections.unmodifiableList(middle);
                this.last = last;
        }

        public NameOfPerson() {
                this("", List.of(), "");
        }

        public NameOfPerson(String first, String last) {
                this(first, List.of(), last);
        }

        public NameOfPerson(String first, String middle, String last) {
                this(first, List.of(middle), last);
        }
}
