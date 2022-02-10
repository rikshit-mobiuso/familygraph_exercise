package com.mo.exercise.graph.utility;

import com.mo.exercise.graph.peoplegraph.PeopleGraph;
import com.mo.exercise.graph.entities.Person;
import com.mo.exercise.graph.constants.FilePaths;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvDataReader {

    private static CSVReader reader;

    public static void readFromCsv() throws IOException, CsvException {
            readPeople();
            readRelationships();
    }

    private static void readPeople() throws IOException, CsvException {
        reader = new CSVReader(new FileReader(FilePaths.PEOPLE_CSV_FILE_PATH));
        Map<String, Person> people = reader.readAll()
                .stream()
                .map(Person::new)
                .collect(Collectors.toMap(Person::getEmail, Function.identity()));
        PeopleGraph.setPersonMap(people);
    }

    private static void readRelationships() throws IOException, CsvException {
        reader = new CSVReader(new FileReader(FilePaths.RELATIONSHIPS_CSV_FILE_PATH));
        reader.readAll().forEach(data -> {
            if (data.length == 3) {
                String email1 = data[0], relation = data[1], email2 = data[2];
                Person p1 = PeopleGraph.getPersonByEmail(email1), p2 = PeopleGraph.getPersonByEmail(email2);
                if (relation.equals("FAMILY"))
                    PeopleGraph.addToFamily(p1, p2);
                else if(relation.equals("FRIEND"))
                    PeopleGraph.addToFriends(p1, p2);
            }
        });
    }
}