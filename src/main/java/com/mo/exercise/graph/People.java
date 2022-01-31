package com.mo.exercise.graph;

import java.util.*;

public class People
{
    private String name;
    private String email;
    private int age;
    boolean visited = false;
    private List<Relatives> relations = new ArrayList<>();

    public People(String name, String email, int age)
    {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getRelationShipCount()
    {
        return this.relations.size();
    }

    public void addRelationShip(People people, String relationShip)
    {
        if (this.getRelationShipCount() > 0)
        {
            this.getRelations().forEach(rel -> {
                // if user already in the relationship list then don't add the user in the
                // relations
                if (rel.getPerson().getEmail().equals(people.getEmail()))
                {
                    return;
                }
            });
        }
        Relatives newRelationShip = new Relatives(Relation.valueOf(relationShip), people);
        this.relations.add(newRelationShip);
        // bi-directional relation
        newRelationShip = new Relatives(Relation.valueOf(relationShip), this);
        people.getRelations().add(newRelationShip);
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public int getAge()
    {
        return age;
    }

    public List<Relatives> getRelations()
    {
        return relations;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    @Override
    public String toString()
    {
        return "name: " + this.getName() + " email: " + this.getEmail() + " age: " + this.getAge();
    }
}