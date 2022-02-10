package com.mo.exercise.graph;
import com.mo.exercise.graph.entities.Person;
import com.mo.exercise.graph.peoplegraph.PeopleGraph;
import org.junit.Test;
import static com.mo.exercise.graph.utility.DataUtility.readFromCsv;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class PeopleGraphTest {

    @Test
    public void testPeopleMapIsCorrectlyPopulated() {

        readFromCsv();
        assertEquals(12, PeopleGraph.size());
    }

    @Test
    public void testPeopleRelationships() {
        Map<String, Integer> numberOfRelationsByUser = new HashMap<String, Integer>();
        numberOfRelationsByUser.put("bob@bob.com", 4);
        numberOfRelationsByUser.put("jenny@toys.com", 3);
        numberOfRelationsByUser.put("nigel@marketing.com", 2);
        numberOfRelationsByUser.put("alan@lonely.org", 0);

        numberOfRelationsByUser.forEach((email, num) -> {
            Person person = PeopleGraph.getPersonByEmail(email);
            assertEquals(num, person.getRelationshipNumber());
        });
    }

    @Test
    public void testExtendedFamilySize() {
        Person jenny = PeopleGraph.getPersonByEmail("jenny@toys.com");
        Person bob = PeopleGraph.getPersonByEmail("bob@bob.com");

        assertEquals(4, PeopleGraph.getExtendedFamilySize(jenny));
        assertEquals(4, PeopleGraph.getExtendedFamilySize(bob));
    }
}