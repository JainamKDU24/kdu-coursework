package kdu.assignment;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    public static void GenerateFixtures() throws Exception {
        List<String[]> csvData = Add();

        try (CSVWriter writer = new CSVWriter(new FileWriter("Fixtures.csv"))) {
            writer.writeAll(csvData);
        }

    }
    private static List<String[]> Add() {
        String[] header = {"Date","Match Number","Team Home","Team Away","Ground"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        String[] Teams={"CSK","MI","RCB","KKR","PBKS","DC","RR","SRH"};

        int matches= Teams.length*(Teams.length-1);
        return list;
    }

}
