package com.dps.jpa.existing.controller;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid; 

import com.dps.jpa.existing.model.Department;
import com.dps.jpa.existing.service.ExistingService;
import com.dps.jpa.existing.service.exception.NotFoundException;
import com.dps.jpa.existing.service.exception.ServerErrorException;

@RestController
public class ExistingController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExistingController.class);
	private final ExistingService existingService;
	
	@Autowired
	public ExistingController(final ExistingService existingService)
	{
		this.existingService = existingService;
	}
	
	@ApiOperation(value = "Retrieve a single department by id",
			response = Department.class)	
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = {
			"application/json" })
	public Department getDepartment(@PathVariable String id)
	{
		LOGGER.debug("Received request to get department <" + id + ">");
		Department deptval = existingService.getDepartment(id);
		
		return deptval;
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

}
