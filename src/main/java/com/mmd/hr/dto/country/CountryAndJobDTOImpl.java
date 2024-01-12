package com.mmd.hr.dto.country;

import com.mmd.hr.dto.country.CountryAndJobDTO;

public class CountryAndJobDTOImpl implements CountryAndJobDTO {

	private String key;
	private String value;

	public CountryAndJobDTOImpl(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CountryAndJobDTOImpl{" +
				"key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
