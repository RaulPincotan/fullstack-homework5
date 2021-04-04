package ro.fasttrackit.fullstackhomework5.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.fullstackhomework5.domain.Country;
import ro.fasttrackit.fullstackhomework5.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getCountries(@RequestParam(required = false) String includeNeighbour,
                                      @RequestParam(required = false) String excludeNeighbour) {
        return countryService.getCountries(includeNeighbour, excludeNeighbour);
    }

    @GetMapping("/names")
    public List<String> getCountriesName() {
        return countryService.getCountiresName();
    }

    @GetMapping("/{countryId}/capital")
    public String getCapital(@PathVariable int countryId) {
        return countryService.getCapital(countryId);
    }

    @GetMapping("/{countryId}/population")
    public Long getPopulation(@PathVariable int countryId) {
        return countryService.getPopulation(countryId);
    }

    @GetMapping("/{countryId}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable int countryId) {
        return countryService.getNeighbours(countryId);
    }

    @GetMapping("/population")
    public Map<String, Long> mapCountriesToPupulation() {
        return countryService.mapCountriesToPopulation();
    }
}
