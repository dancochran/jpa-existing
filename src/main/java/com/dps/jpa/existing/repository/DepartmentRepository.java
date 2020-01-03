package com.dps.jpa.existing.repository;

import com.dps.jpa.existing.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String>
{
	
}
