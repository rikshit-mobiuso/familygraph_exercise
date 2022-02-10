package com.mo.exercise.graph.utility;

import com.mo.exercise.graph.peoplegraph.PeopleGraph;
import com.mo.exercise.graph.entities.Person;
import com.mo.exercise.graph.constants.FilePaths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mo.exercise.graph.peoplegraph.PeopleGraph.addToRelationMaps;

public class CsvDataReader {

    public static void readFromCsv(){
            readPeople();
            addToRelationMaps();
    }

    private static void readPeople(){

        HashMap<String, Person> personMap =new HashMap<>();

        try (FileReader fr = new FileReader(FilePaths.PEOPLE_CSV_FILE_PATH);
             BufferedReader br = new BufferedReader(fr);){

            // read line by line
            String line;
            while((line = br.readLine()) != null){
                String[] person = line.split(",");
                Person p = new Person(person[0],person[1],Short.valueOf(person[2]));
                personMap.put(person[1],p);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PeopleGraph.setPersonMap(personMap);
    }

    public static List<String> readRelationships() {
        List<String> relationshipList = new ArrayList<>();

        try (FileReader fr = new FileReader(FilePaths.RELATIONSHIPS_CSV_FILE_PATH);
        BufferedReader br = new BufferedReader(fr);) {
            // read line by line

            String line;
            while ((line=br.readLine()) !=null) {
                if(line.trim().length()==0) {
                    continue;
                }
                relationshipList.add(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n",e);
        }
        return relationshipList;
    }
}