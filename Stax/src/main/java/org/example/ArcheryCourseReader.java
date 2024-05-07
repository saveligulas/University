package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ArcheryCourseReader {
    static FileWriter output;
    static int indentLevel = 0;

    public static void main(String[] args) {
        Reader input;
        try {
            input = new FileReader("Stax/src/main/resources/3DArcheryCourses.xml");
            output = new FileWriter("Stax/src/main/resources/archery_tournament.json");

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(input);

            List<Type> type = parseTypes(reader);
            List<Target> targets = parseTargets(reader);
            List<Participant> participants = parseParticipants(reader);
            List<Course> courses = parseCourses(reader);


            writeStartObject();
            writeArrayStart("targets", 0);
            for (int i = 0; i < targets.size(); i++) {
                output.write("\n");
                writeTarget(targets.get(i));
                if (i != targets.size() - 1) {
                    output.write(",");
                }
            }
            output.write("]");
            output.write(",");
            writeArrayStart("courses", 0);
            for (int i = 0; i < courses.size(); i++) {
                output.write("\n");
                writeCourse(courses.get(i));
                if (i != courses.size() - 1) {
                    output.write(",");
                }
            }
            output.write("]");
            output.write(",");
            writeArrayStart("types", 0);
            for (int i = 0; i < type.size(); i++) {
                output.write("\n");
                writeType(type.get(i));
                if (i != type.size() - 1) {
                    output.write(",");
                }
            }
            output.write("]");
            output.write(",");
            writeArrayStart("participants", 0);
            for (int i = 0; i < participants.size(); i++) {
                output.write("\n");
                writeParticipant(participants.get(i), 0);
                if (i != participants.size() - 1) {
                    output.write(",");
                }
            }
            writeEndObject();
            output.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static void writeStartObject() throws IOException {
        output.write('{');
        indentLevel++;
    }

    static void writeEndObject() throws IOException {
        indentLevel--;
        output.write('}');
    }

    static void writeKeyValue(String key, String value, int indent) throws IOException {
        indent(indent);
        output.write('"' + key + "\": \"" + value + '"');
    }

    static void writeArrayStart(String name, int indent) throws IOException {
        indent(indent);
        output.write('"' + name + "\": [");
        indentLevel++;
    }

    static void writeArrayEnd(int indent) throws IOException {
        indentLevel--;
        indent(indent);
        output.write(']');
    }

    static void indent(int level) throws IOException {
        for (int i = 0; i < level; i++) {
            output.write('\t');
        }
    }

    static List<Course> parseCourses(XMLStreamReader reader) throws XMLStreamException, IOException {
        List<Course> courses = new ArrayList<>();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLEvent.START_ELEMENT) {
                if (reader.isStartElement()) {
                    String name = reader.getLocalName();

                    if (name.equals("course")) {
                        String id = reader.getAttributeValue( null, "id");

                        reader.nextTag();
                        String nam =reader.getElementText().trim();

                        reader.nextTag();
                        String location = reader.getElementText();
                        courses.add(new Course(id, name, location));
                    }
                }
            }
        }
        return courses;
    }

    static List<Target> parseTargets(XMLStreamReader reader) throws XMLStreamException, IOException {
        List<Target> targets = new ArrayList<>();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLEvent.START_ELEMENT) {
                String name = reader.getLocalName();

                if (name.equals("target")) {
                    String targetId = reader.getAttributeValue(null, "id");
                    String typeId = reader.getAttributeValue(null, "type_id");
                    String content = reader.getElementText().trim();
                    targets.add(new Target(targetId, typeId, content));
                } else if (name.equals("person")) {
                    break;
                }
            }
        }
        return targets;
    }

    static List<Type> parseTypes(XMLStreamReader reader) throws XMLStreamException {
        List<Type> participants = new ArrayList<>();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLEvent.START_ELEMENT) {
                String name = reader.getLocalName();

                if (name.equals("type")) {
                    String id = reader.getAttributeValue(null, "id");
                    String groupId = reader.getAttributeValue(null, "point_sheet_id");
                    String nameValue = reader.getElementText().trim();
                    participants.add(new Type(id, groupId, nameValue));
                } else if (name.equals("target")) {
                    break;
                }
            }
        }
        return participants;
    }

    static List<Participant> parseParticipants(XMLStreamReader reader) throws XMLStreamException, IOException {
        List<Participant> participants = new ArrayList<>();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLEvent.START_ELEMENT) {
                String name = reader.getLocalName();

                if (name.equals("person")) {
                    String id = reader.getAttributeValue(null, "id");
                    String groupId = reader.getAttributeValue(null, "group_id");
                    String nameValue = reader.getElementText().trim();
                    participants.add(new Participant(id, groupId, nameValue));
                } else if (name.equals("course")) {
                    break;
                }
            }
        }
        return participants;
    }

    static void writeTarget(Target target) throws IOException {
        writeStartObject();
        writeKeyValue("target_id", target.getTargetId(), 0);
        output.write(",");
        writeKeyValue("type_id", target.getTypeId(), 0);
        output.write(",");
        writeKeyValue("content", target.getContent(), 0);
        writeEndObject();
    }

    static void writeType(Type target) throws IOException {
        writeStartObject();
        writeKeyValue("type_id", target.getId(), 0);
        output.write(",");
        writeKeyValue("point_sheet_id", target.getPointSheetId(), 0);
        output.write(",");
        writeKeyValue("content", target.getName(), 0);
        writeEndObject();
    }

    static void writeCourse(Course course) throws IOException {
        writeStartObject();
        writeKeyValue("course_id", course.getId(), 0);
        output.write(",");
        writeKeyValue("name", course.getName(), 0);
        output.write(",");
        writeKeyValue("content", course.getLocationDescription(), 0);
        writeEndObject();
    }
    static void writeParticipant(Participant participant, int index) throws IOException {
        writeStartObject();
        writeKeyValue("id", participant.getId(), 0);
        output.write(",");
        writeKeyValue("group_id", participant.getGroupId(), 0);
        output.write(",");
        writeKeyValue("name", participant.getName(), 0);
        writeEndObject();
    }

    private static class Participant {
        private String id;
        private String groupId;
        private String name;

        public Participant(String id, String groupId, String name) {
            this.id = id;
            this.groupId = groupId;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getName() {
            return name;
        }
    }

    private static class Target {
        private String targetId;
        private String typeId;
        private String content;

        public Target(String targetId, String typeId, String content) {
            this.targetId = targetId;
            this.content = content;
        }

        public String getTargetId() {
            return targetId;
        }

        public String getTypeId() {
            return typeId;
        }

        public String getContent() {
            return content;
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Type {
        private String id;
        private String pointSheetId;
        private String name;
    }

    @AllArgsConstructor
    private static class Course {
        private String id;
        private String name;
        private String locationDescription;



        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setLocationDescription(String locationDescription) {
            this.locationDescription = locationDescription;
        }

        public String getLocationDescription() {
            return locationDescription;
        }

    }

}