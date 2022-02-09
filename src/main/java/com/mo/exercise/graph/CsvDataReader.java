package com.mo.exercise.graph;

import com.mo.exercise.graph.CsvPaths;
import com.mo.exercise.graph.RelationshipName;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class CsvDataReader {

    private static CSVReader reader;

    // This Application makes use of OpenCSV library to Read Csv files
    public static void readFromCsv() throws IOException, CsvException {
            readPeople();
            readRelationships();
    }

    private static void readPeople() throws IOException, CsvException {
        reader = new CSVReader(new FileReader(CsvPaths.FILEPATHOFPEOPLECSV));
        Map<String, Person> people = reader.readAll()
                .stream()
                .map(Person::new)
                .collect(Collectors.toMap(Person::getEmail, Function.identity()));
        People.setMap(people);
    }

    private static void readRelationships() throws IOException, CsvException {
        reader = new CSVReader(new FileReader(CsvPaths.FILEPATHOFRELATIONSHIPSCSV));
        reader.readAll().forEach(data -> {
            if (data.length == 3) {
                String email1 = data[0], relation = data[1], email2 = data[2];
                Person p1 = People.getPersonByEmail(email1), p2 = People.getPersonByEmail(email2);
                if (relation.equals(RelationshipName.FAMILY))
                    People.addToFamily(p1, p2);
                else
                    People.addToFriends(p1, p2);
            }
        });
    }
}
