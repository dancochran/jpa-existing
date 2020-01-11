package com.dps.jpa.existing.controller;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiOperation;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import javax.validation.Valid; 

import com.dps.jpa.existing.model.Department;
import com.dps.jpa.existing.model.DepartmentDto;
import com.dps.jpa.existing.service.ExistingService;
import com.dps.jpa.existing.service.exception.NotFoundException;
import com.dps.jpa.existing.service.exception.ServerErrorException;

@RestController
public class ExistingController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExistingController.class);
	private final ExistingService existingService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ExistingController(final ExistingService existingService, final ModelMapper modelMapper)
	{
		this.existingService = existingService;
		this.modelMapper = modelMapper;
	}
	
	@ApiOperation(value = "Retrieve a single department by id",
			response = Department.class)	
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = {
			"application/json" })
	public DepartmentDto getDepartment(@PathVariable String id)
	{
		LOGGER.debug("Received request to get department <" + id + ">");
		Department deptval = existingService.getDepartment(id);
		
		return convertToDto(deptval);
	}

	@ApiOperation(value = "Update a department")
	@RequestMapping(value = "/department", method = RequestMethod.PUT, consumes = {
			"application/json" })
	// DSC - also consider PATCH for updates of only sent fields/keys
	public Department updateDepartment(@RequestBody @Valid final Department department)
	{
		LOGGER.debug("Received request to update department {}", department);
		try
		{
			Department deptval = existingService.updateDepartment(department);
			return deptval;
		}
		catch (RuntimeException rex)
		{
			LOGGER.error(rex.toString());
			throw rex;
		}
	}
	
	@ApiOperation(value = "Delete a single department by id.")	
	@RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
	public void deleteDepartment(@PathVariable String id)
	{
		LOGGER.debug("Received request to delete department <" + id + ">");
		existingService.deleteDepartment(id);
	}

	@ApiOperation(value = "Add a new department.",
			response = Department.class)
	@RequestMapping(value = "/department", method = RequestMethod.POST, consumes = {
			"application/json" })
	public Department addDepartment(@RequestBody @Valid final Department department)
	{
		LOGGER.debug("Received request to add new department {}", department);
		
		return existingService.addDepartment(department);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleServerErrorException(ServerErrorException e)
	{
		return e.getMessage();
	}	

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleServerErrorException(NotFoundException e)
	{
		return e.getMessage();
	}

	private DepartmentDto convertToDto(Department dept)
	{
		DepartmentDto deptDto = modelMapper.map(dept, DepartmentDto.class);
		// call any custom methods in the DTO like a date converter, etc.
		return deptDto;
	}
	
	private Department convertToEntity(DepartmentDto deptDto) throws ParseException 
	{
		Department dept = this.modelMapper.map(deptDto, Department.class);
		// set any special fields that needed converted/formatted by DTO methods
		/***
	    if (deptDto.getDepartmentNumber() != null) 
		{
	        Department oldDept = existingService.getDepartment(deptDto.getDepartmentNumber());
	        dept.setXXX(oldDept.getSpecialThingThatCallerCantSend());
	    }
		 ***/
		
		return dept;
	}

}
