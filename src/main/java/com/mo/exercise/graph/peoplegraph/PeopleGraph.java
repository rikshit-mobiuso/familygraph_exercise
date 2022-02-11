package com.mo.exercise.graph.peoplegraph;
import java.io.IOException;
import java.util.*;

import com.mo.exercise.graph.entities.Person;
import com.mo.exercise.graph.utility.DataUtility;

import static com.mo.exercise.graph.utility.DataUtility.readRelationships;

import com.opencsv.exceptions.CsvException;

public class PeopleGraph {
    private static Map<String, Person> personMap;
    static {
        DataUtility.readFromCsv();
    }

    public static void setPersonMap(Map<String, Person> personMap) {
        PeopleGraph.personMap = personMap;
    }

    public static Person getPersonByEmail(String email) {
        return personMap.get(email);
    }

    public static void addToFamily(Person person1, Person person2) {
        person1.addFamily(person2);
        person2.addFamily(person1);
    }

    public static void addToFriends(Person person1, Person person2) {
        person1.addFriend(person2);
        person2.addFriend(person1);
    }

    public static int size() {
        return personMap.size();
    }

    public static int getExtendedFamilySize(Person person) {
        Set<String> extendedFamilySet = new HashSet<String>();
        return person.getExtendedFamily(extendedFamilySet).size();
    }

    public static void addToRelationMaps() {
        readRelationships().forEach(line-> {
            String[] relationship = line.split(",");
            String email1 = relationship[0], relation = relationship[1], email2 = relationship[2];
                Person p1 = PeopleGraph.getPersonByEmail(email1), p2 = PeopleGraph.getPersonByEmail(email2);
                if (relation.equals("FAMILY"))
                    PeopleGraph.addToFamily(p1, p2);
                else if(relation.equals("FRIEND"))
                    PeopleGraph.addToFriends(p1, p2);
        });
    }

    public static void print() {
        personMap.values().stream()
                .forEach(p -> System.out.println(
                        p.getName() + " : "
                                + "Family: " + p.getFamily().size()
                                + ", Friends: " + p.getFriends().size()
                                +", Extended Family Count : " + getExtendedFamilySize(p)));
    }

    public static void main(String[] args) throws IOException, CsvException {
        print();
    }
}