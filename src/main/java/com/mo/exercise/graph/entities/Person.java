package com.mo.exercise.graph.entities;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private String name;
    private String email;
    private short age;
    private Set<Person> family = new HashSet<Person>();
    private Set<Person> friends = new HashSet<Person>();

    public Person(String name, String email, Short age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public short getAge() {
        return age;
    }
    public Set<Person> getFamily() {
        return family;
    }
    public Set<Person> getFriends() {
        return friends;
    }

    public void addFamily(Person person) {
        this.family.add(person);
    }

    public void addFriend(Person friend) {
        this.friends.add(friend);
    }

    public Integer getRelationshipNumber() {
        return this.family.size() + this.friends.size();
    }

    public Set<String> getExtendedFamily(Set<String> set) {
        set.add(email);
        this.family.forEach(p -> {
            if (!set.contains(p.email))
                p.getExtendedFamily(set);
        });
        return set;
    }
}