package com.dps.jpa.existing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.validation.annotation.Validated;

import com.dps.jpa.existing.model.Department;
import com.dps.jpa.existing.repository.DepartmentRepository;
import com.dps.jpa.existing.service.exception.NotFoundException;

@Service
@Validated
public class ExistingServiceImpl implements ExistingService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExistingServiceImpl.class);
	private DepartmentRepository departmentRepository;
	
	@Autowired
    public ExistingServiceImpl(DepartmentRepository departmentRepository) 
	{
        this.departmentRepository = departmentRepository;
    }

	@Override
	public Department getDepartment(String id)
	{
		LOGGER.debug("ExistingServiceImpl::getDepartment(): retrieving department <" + id + ">");
		return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department with id <" + id + "> not found"));
	}

	@Override
	public Department updateDepartment(Department dept)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDepartment(String id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department addDepartment(Department dept)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
