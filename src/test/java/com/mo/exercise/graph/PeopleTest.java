package com.mo.exercise.graph;

import org.junit.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.mo.exercise.graph.CsvPaths;
import com.mo.exercise.graph.Person;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PeopleTest {

    CSVReader reader;

    @Test
    public void testPeopleMapIsCorrectlyPopulated() throws IOException, CsvException {
        assertFalse(People.isEmpty());

        reader = new CSVReader(new FileReader(CsvPaths.FILEPATHOFPEOPLECSV));

        int size = reader.readAll().size();
        assertEquals(size, People.size());
    }

    @Test
    public void testPeopleRelationships() throws IOException, CsvException {
        Map<String, Integer> numberOfRelationsByUser = new HashMap<String, Integer>();
        numberOfRelationsByUser.put("bob@bob.com", 4);
        numberOfRelationsByUser.put("jenny@toys.com", 3);
        numberOfRelationsByUser.put("nigel@marketing.com", 2);
        numberOfRelationsByUser.put("alan@lonely.org", 0);

        numberOfRelationsByUser.forEach((email, num) -> {
            Person person = People.getPersonByEmail(email);
            assertEquals(num, person.getRelationshipNumber());
        });
    }

    @Test
    public void testExtendedFamilySize() {
        Person jenny = People.getPersonByEmail("jenny@toys.com");
        Person bob = People.getPersonByEmail("bob@bob.com");

        assertEquals(4, People.getExtendedFamilySize(jenny));
        assertEquals(4, People.getExtendedFamilySize(bob));
    }

}