package com.mmd.hr.dto;

public class DepartmentDTOImpl implements DepartmentDTO {

	private int key;
	private String value;

	public DepartmentDTOImpl(int key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "DepartmentDTOImpl{" +
				"key=" + key +
				", value='" + value + '\'' +
				'}';
	}
}
