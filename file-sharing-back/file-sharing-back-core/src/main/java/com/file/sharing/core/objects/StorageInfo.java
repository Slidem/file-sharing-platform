package com.file.sharing.core.objects;

/**
 * 
 * @author Andrei Aioanei
 * @created Dec 10, 2017
 *
 */

public class StorageInfo {
	private String location;
	private Long occupiedSize;
	
	public StorageInfo(String location, Long occupiedSize) {
		super();
		this.location = location;
		this.occupiedSize = occupiedSize;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getOccupiedSize() {
		return occupiedSize;
	}

	public void setOccupiedSize(Long occupiedSize) {
		this.occupiedSize = occupiedSize;
	}

	@Override
	public String toString() {
		return "StorageInfo [location=" + location + ", occupiedSize=" + occupiedSize + "]";
	}
	
}
