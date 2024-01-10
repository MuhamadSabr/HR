package com.mmd.hr.reposiroty;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {

	@Query("SELECT c.id FROM Country c WHERE c.countryName = :countryName")
	String getCountryIdByCountryName(@Param("countryName") String countryName);

	@Query("SELECT c.countryName FROM Country c WHERE c.countryId = :countryId")
	String getCountryNameByCountryId(@Param("countryId") String countryName);

	@Query("SELECT new com.mmd.hr.dto.CountryAndJobDTOImpl(countryId as key, countryName as value) FROM Country")
	List<CountryAndJobDTO> getCountryIdAndCountryName();
}
