package com.mo.exercise.graph.datautility;

public class CsvDataReader {
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
}
