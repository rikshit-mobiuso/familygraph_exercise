package com.mo.exercise.graph.peoplegraph;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mo.exercise.graph.entities.Person;
import com.mo.exercise.graph.utility.CsvDataReader;
import com.opencsv.exceptions.CsvException;

public class PeopleGraph {
    private static Map<String, Person> personMap;

    static {
        try {
            CsvDataReader.readFromCsv();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
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

    public static boolean isEmpty() {
        return personMap.isEmpty();
    }

    public static int size() {
        return personMap.size();
    }

    public static int getExtendedFamilySize(Person person) {
        Set<String> extendedFamilySet = new HashSet<String>();
        return person.getExtendedFamily(extendedFamilySet).size();
    }

    public static void print() {
        personMap.values().stream()
                .forEach(p -> System.out.println(
                        p.getName() + " : "
                                + "Family: " + p.getFamily().size()
                                + ", Friends: " + p.getFriends().size()
                                +", Extended Family Count : " + getExtendedFamilySize(p)));
    }

    public static void main(String[] args) {
        print();
    }
}