package com.file.sharing.core.objects;

/**
 * 
 * @author Andrei Aioanei
 * @created Dec 10, 2017
 *
 */

public class StorageInfo {
	private String location;
	private long size;
	
	public StorageInfo(String location, long size) {
		super();
		this.location = location;
		this.size = size;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "StorageInfo [location=" + location + ", size=" + size + "]";
	}
	
}
