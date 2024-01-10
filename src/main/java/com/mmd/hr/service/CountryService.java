package com.mmd.hr.service;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.entity.Country;
import com.mmd.hr.reposiroty.CountryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    public String getCountryIdByName(String countryName) {return countryRepository.getCountryIdByCountryName(countryName);}

    public String getCountryNameById(String countryId) {return countryRepository.getCountryNameByCountryId(countryId);}

    public List<CountryAndJobDTO> getCountryIdAndName() {return countryRepository.getCountryIdAndCountryName();}
}
