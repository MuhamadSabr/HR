package com.mmd.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "locations")
public class Address {

	@Column(name = "location_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressGenerator")
	@SequenceGenerator(name = "addressGenerator", sequenceName = "locations_seq", allocationSize = 100)
	private int locationId;

	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "city")
	@NotNull(message = "City is required")
	@Size(min = 2, message = "City is required")
	private String city;

	@Column(name = "state_province")
	private String stateProvince;

	@Column(name = "country_id")
	private String countryId;

	public Address(){}

	public Address(String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
		this.stateProvince = stateProvince;
		this.countryId = countryId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Address{" +
				"locationId=" + locationId +
				", streetAddress='" + streetAddress + '\'' +
				", postalCode='" + postalCode + '\'' +
				", city='" + city + '\'' +
				", stateProvince='" + stateProvince + '\'' +
				", countryId='" + countryId + '\'' +
				'}';
	}
}
