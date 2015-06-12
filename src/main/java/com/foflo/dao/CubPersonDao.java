package com.foflo.dao;

public interface CubPersonDao {
	
	public Integer getPersonNameCountForMergeProfile(long personId, String firstName, String lastName);
	
	public Integer getPersonImageCount(long personId);
}
