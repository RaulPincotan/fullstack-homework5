package ro.fasttrackit.fullstackhomework5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.fullstackhomework5.domain.Country;
import ro.fasttrackit.fullstackhomework5.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/continents")
public class ContinentController {
    private final CountryService countryService;

    public ContinentController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getCountriesInContinent(@RequestParam(required = false) String continent,
                                                 @RequestParam(required = false) long population) {
        return countryService.getCountriesInContinent(continent, population);
    }

    @GetMapping("/countries")
    public Map<String, List<Country>> mapContinentToCountries() {
        return countryService.mapContinentToCountries();
    }


}
