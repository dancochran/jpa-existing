package com.dps.jpa.existing.service;

import com.dps.jpa.existing.model.Department;

public interface ExistingService
{
	Department getDepartment(String id);
	Department updateDepartment(Department dept) ;
	void deleteDepartment(String id);
	Department addDepartment(Department dept);
	
}
