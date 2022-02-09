package com.mo.exercise.graph.utility;

import com.mo.exercise.graph.peoplegraph.People;
import com.mo.exercise.graph.entities.Person;
import com.mo.exercise.graph.constants.CsvPaths;
import com.mo.exercise.graph.constants.RelationshipType;
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
        reader = new CSVReader(new FileReader(CsvPaths.PEOPLE_CSV_FILE_PATH));
        Map<String, Person> people = reader.readAll()
                .stream()
                .map(Person::new)
                .collect(Collectors.toMap(Person::getEmail, Function.identity()));
        People.setMap(people);
    }

    private static void readRelationships() throws IOException, CsvException {
        reader = new CSVReader(new FileReader(CsvPaths.RELATIONSHIPS_CSV_FILE_PATH));
        reader.readAll().forEach(data -> {
            if (data.length == 3) {
                String email1 = data[0], relation = data[1], email2 = data[2];
                Person p1 = People.getPersonByEmail(email1), p2 = People.getPersonByEmail(email2);
                if (relation.equals(RelationshipType.FAMILY))
                    People.addToFamily(p1, p2);
                else
                    People.addToFriends(p1, p2);
            }
        });
    }
}
