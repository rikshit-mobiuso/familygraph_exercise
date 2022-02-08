package com.mo.exercise.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class DataUtility
{
    String filePathOfPeopleCsv = "src/test/resources/people.csv";
    String filePathOfRelationshipsCsv = "src/test/resources/relationships.csv";

//    public List<Relatives> getAllPeopleRelationShip(List<People> listPeople)
//    {
//        List<Relatives> relationshipsList = new ArrayList<>();
//        try (FileReader fr = new FileReader(filePathOfRelationshipsCsv);
//             BufferedReader br = new BufferedReader(fr);)
//        {
//            // read line by line
//            String line;
//            while ((line = br.readLine()) != null)
//            {
//                if (line.trim().length() == 0)
//                {
//                    continue; // Skip blank lines
//                }
//                String[] rr = line.split(",");
//                People currentPerson = getPersonByEmail(listPeople, rr[0]);
//                People nextPerson = getPersonByEmail(listPeople, rr[2]);
//                if (null != nextPerson && null != currentPerson)
//                {
//                    currentPerson.addRelationShip(nextPerson, rr[1]);
//                }
//            }
//
//        }
//        catch (IOException e)
//        {
//            System.err.format("IOException: %s%n", e);
//        }
//        return relationshipsList;
//    }

    private People getPersonByEmail(List<People> peopleList, String email)
    {
        List<People> listPeople = peopleList.stream().filter(people -> people.getEmail().equals(email))
                .collect(Collectors.toList());
        if (null != listPeople && !listPeople.isEmpty())
        {
            return listPeople.get(0);
        }
        return null;
    }

    public static void main(String[] args) throws IOException
    {
        DataUtility r = new DataUtility();
        CsvDataReader csvDataReader = new CsvDataReader();
        //System.out.println(allPeople.toString());

        List<People> peopleList = csvDataReader.getAllPeople();
        List<String> relationshipList = csvDataReader.getAllPeopleRelationShip(peopleList);
        HashMap<People,List<Relatives>> graphOfRelations = r.mapAllRelationship(peopleList,relationshipList);

        graphOfRelations.forEach( (people,relativesList) -> {
            System.out.print(people.getName() + " : " + relativesList.size() + " : ");
            relativesList.forEach(relatives -> {
                System.out.print(relatives.toString() + " ");
            });
            System.out.println();
            System.out.println(people.getName() + " : " + r.getExtendedFamilyCount(people,graphOfRelations));
        });

    }

    public List<Relatives> getRelativeList(People people,List<People> peopleList, List<String> relationshipList){
        List<Relatives> relativesList = new ArrayList<>();

        relationshipList.forEach(line -> {
            String[] rel = line.split(",");

            if(people.getEmail().equals(rel[0])){
                Relation relation = Relation.valueOf(rel[1]);
                People anotherPerson = getPersonByEmail(peopleList, rel[2]);

                relativesList.forEach(relatives -> {
                    if(relatives.getPerson().getEmail().equals(anotherPerson.getEmail())){
                        return;
                    }
                });

                Relatives relatives = new Relatives(relation,anotherPerson);
                relativesList.add(relatives);

            }else if(people.getEmail().equals(rel[2])){ // for bi-direction relationship
                Relation relation = Relation.valueOf(rel[1]);
                People anotherPerson = getPersonByEmail(peopleList, rel[0]);

                relativesList.forEach(relatives -> {
                    if(relatives.getPerson().getEmail().equals(anotherPerson.getEmail())){
                        return;
                    }
                });

                Relatives relatives = new Relatives(relation,anotherPerson);
                relativesList.add(relatives);
            }
        });
        return relativesList;
    }

    public HashMap<People,List<Relatives>> mapAllRelationship(List<People> peopleList, List<String> relationshipList){
        HashMap<People,List<Relatives>> graphOfRelations = new HashMap<>();

        peopleList.forEach(people -> {
            List<Relatives> relativesList = getRelativeList(people,peopleList,relationshipList);
            graphOfRelations.put(people,relativesList);
        });

        return graphOfRelations;
    }

    public int getExtendedFamilyCount(People people,HashMap<People,List<Relatives>> graphOfRelations)
    {
        Stack<People> stack = new Stack<>();

        if (null != people)
        {
            stack.push(people);
            people.setVisited(true);
        }
        int extendedFamilyCount = 0;
        while (!stack.isEmpty())
        {
            People poped = stack.pop();
            extendedFamilyCount++;
            graphOfRelations.get(poped).forEach(rel -> {
                if (!rel.getPerson().isVisited() && rel.getRelationShip().equals(Relation.FAMILY))
                {
                    stack.push(rel.getPerson());
                    rel.getPerson().setVisited(true);
                }
            });
        }
        graphOfRelations.forEach( (person,relation) ->{
            person.setVisited(false);
        });
        return extendedFamilyCount;
    }

}
