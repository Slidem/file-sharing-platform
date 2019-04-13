package com.file.sharing.core.objects;

/**
 * 
 * @author Andrei Aioanei
 * @created Dec 10, 2017
 *
 */

public class StorageInfo {
	private String location;
	private Long usedSpaceSize;
	
	public StorageInfo(String location, Long usedSpaceSize) {
		super();
		this.location = location;
		this.usedSpaceSize = usedSpaceSize;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getUsedSize() {
		return usedSpaceSize;
	}

	public void setUsedSpaceSize(Long usedSpaceSize) {
		this.usedSpaceSize = usedSpaceSize;
	}

	@Override
	public String toString() {
		return "StorageInfo [location=" + location + ", usedSpaceSize=" + usedSpaceSize + "]";
	}
	
}
