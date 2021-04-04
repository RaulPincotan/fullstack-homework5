package ro.fasttrackit.fullstackhomework5.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.fullstackhomework5.domain.Country;

import java.util.List;

@Profile("memoryreader")
@Component
public class CountryReaderFromMemory implements CountryReader {
    @Override
    public List<Country> readCountries() {
        return List.of(new Country(500, "Oradea", "Rogerius", 210000, 110, "Bihor", List.of()));
    }
}
