package com.mo.exercise.graph;

import java.util.*;

public class People
{
    private String name;
    private String email;
    private int age;
    boolean visited = false;

    public People(String name, String email, int age)
    {
        this.name = name;
        this.email = email;
        this.age = age;
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