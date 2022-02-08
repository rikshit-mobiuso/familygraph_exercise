package com.mo.exercise.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvDataReader {
    String filePathOfPeopleCsv = "src/test/resources/people.csv";
    String filePathOfRelationshipsCsv = "src/test/resources/relationships.csv";

    public List<People> getAllPeople()
    {
        List<People> peopleList = new ArrayList<>();
        try (FileReader fr = new FileReader(filePathOfPeopleCsv);
             BufferedReader br = new BufferedReader(fr);)
        {
            // read line by line
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] peoples = line.split(",");
                People p = new People(peoples[0], peoples[1], Integer.valueOf(peoples[2]));
                peopleList.add(p);
            }

        }
        catch (IOException e)
        {
            System.err.format("IOException: %s%n", e);
        }
        return peopleList;
    }

    public List<String> getAllPeopleRelationShip(List<People> listPeople)
    {
        List<String> relationshipsList = new ArrayList<>();
        try (FileReader fr = new FileReader(filePathOfRelationshipsCsv);
             BufferedReader br = new BufferedReader(fr);)
        {
            // read line by line
            String line;
            while ((line = br.readLine()) != null)
            {
                if (line.trim().length() == 0)
                {
                    continue; // Skip blank lines
                }
                relationshipsList.add(line);
            }
        }
        catch (IOException e)
        {
            System.err.format("IOException: %s%n", e);
        }
        return relationshipsList;
    }
}
