package ro.fasttrackit.fullstackhomework5.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.fullstackhomework5.domain.Country;
import ro.fasttrackit.fullstackhomework5.exceptions.CountryNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final CountryReader countryReader;
    private final List<Country> countries = new ArrayList<>();


    public CountryService(CountryReader countryReader) {
        this.countryReader = countryReader;
        countries.addAll(countryReader.readCountries());
    }

    public List<Country> getCountries(String includeNeighbour, String excludeNeighbour) {
        return countries.stream()
                .filter(country -> country.getNeighbours().contains(includeNeighbour))
                .filter(country -> !country.getNeighbours().contains(excludeNeighbour))
                .collect(Collectors.toList());
    }

    public List<String> getCountiresName() {
        return countries.stream()
                .map(country -> country.getName())
                .collect(Collectors.toList());
    }

    public String getCapital(int id) {
        return countries.stream()
                .filter(country -> country.getId() == id)
                .map(country -> country.getCapital())
                .findFirst().orElse("No country with id " + id);
    }

    public Long getPopulation(int countryId) {
        return countries.stream()
                .filter(country -> country.getId() == countryId)
                .map(country -> country.getPopulation())
                .findFirst().orElseThrow(() -> new CountryNotFoundException("Country with id " + countryId + " does not exist"));
    }

    public List<Country> getCountriesInContinent(String continent, long population) {
        if (continent == null) {
            return countries;
        } else {
            return countries.stream()
                    .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                    .filter(country -> country.getPopulation() > population)
                    .collect(Collectors.toList());
        }
    }

    public List<String> getNeighbours(int countryId) {
        return countries.stream()
                .filter(country -> country.getId() == countryId)
                .findFirst()
                .get().getNeighbours();
    }


    public Map<String, Long> mapCountriesToPopulation() {
        return countries.stream()
                .collect(Collectors.toMap(country -> country.getName(), country -> country.getPopulation()));
    }

    public Map<String, List<Country>> mapContinentToCountries() {
        return countries.stream()
                .collect(Collectors.groupingBy(country -> country.getContinent()));
    }
}
