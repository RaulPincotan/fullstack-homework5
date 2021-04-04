package ro.fasttrackit.fullstackhomework5.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.fullstackhomework5.domain.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Profile("filereader")
@Component
public class CountryReaderFromFile implements CountryReader {
    public List<Country> readCountries() {
        List<Country> result = new ArrayList<>();
        String line = "";
        int id = 1;
        try (BufferedReader bfr = new BufferedReader(new FileReader("src/main/resources/countries.txt"))) {
            while ((line = bfr.readLine()) != null) {
                result.add(getCountry(id++, line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private Country getCountry(int id, String line) {
        String[] arr = line.split("\\|");
        List<String> neighbours = new ArrayList<>();
        if (arr.length > 5) {
            neighbours = fetchNeighbours(arr[5]);
        }

        return new Country(id, arr[0],
                arr[1],
                Long.parseLong(arr[2]),
                Long.parseLong(arr[3]),
                arr[4],
                neighbours);
    }

    private List<String> fetchNeighbours(String s) {
        String[] neighbours = s.split("~");
        List<String> list = new ArrayList<>();
        for (String st : neighbours) {
            list.add(st);
        }
        return list;

    }

}
