package com.mo.exercise.graph;

import junit.framework.TestCase;

import java.util.List;

public class ReadTest extends TestCase
{
    public void testGetAllPeople()
    {
        Read r = new Read();
        List<People> allPeople = r.getAllPeople();

        assertEquals(12,allPeople.size());
    }

    public void testGetRelationShipCount()
    {
        Read r = new Read();
        int bobRelationshipCount = r.getRelationShipCount("Bob");
        int jennyRelationshipCount = r.getRelationShipCount("Jenny");
        int nigelRelationshipCount = r.getRelationShipCount("Nigel");
        int alanRelationshipCount = r.getRelationShipCount("Alan");

        assertEquals(4,bobRelationshipCount);
        assertEquals(3,jennyRelationshipCount);
        assertEquals(2,nigelRelationshipCount);
        assertEquals(0,alanRelationshipCount);
    }

    public void testGetExtendedFamilyCount()
    {
        Read r = new Read();
        int bobExtendedFamilyCount = r.getExtendedFamilyCount("Bob");
        int jennyExtendedFamilyCount = r.getExtendedFamilyCount("Jenny");

        assertEquals(4,bobExtendedFamilyCount);
        assertEquals(4,jennyExtendedFamilyCount);
    }
}