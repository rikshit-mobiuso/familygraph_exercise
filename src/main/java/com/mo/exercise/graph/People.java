package com.mo.exercise.graph;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mo.exercise.graph.CsvDataReader;
import com.mo.exercise.graph.Person;
import com.opencsv.exceptions.CsvException;

import static com.mo.exercise.graph.CsvDataReader.*;

public class People {
    private static Map<String, Person> map;

    static {
        try {
            readFromCsv();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public static void setMap(Map<String, Person> map) {
        People.map = map;
    }

    public static Person getPersonByEmail(String email) {
        return map.get(email);
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
        return map.isEmpty();
    }

    public static int size() {
        return map.size();
    }

    public static int getExtendedFamilySize(Person person) {
        Set<String> set = new HashSet<String>();
        return person.getExtendedFamily(set).size();
    }

    public static void print() {
        map.values().stream()
                .forEach(p -> System.out.println(
                        p.getName() + " " + p.getEmail() + " " + p.getAge()
                                + " Family: " + p.getFamily().size() + " Friends: " + p.getFriends().size()));
    }
}
