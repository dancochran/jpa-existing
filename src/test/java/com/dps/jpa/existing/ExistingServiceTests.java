package com.dps.jpa.existing;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.dps.jpa.existing.model.Department;
import com.dps.jpa.existing.repository.DepartmentRepository;
import com.dps.jpa.existing.service.ExistingServiceImpl;

import java.util.Optional;

@ActiveProfiles("servicetest")
@DataJpaTest
public class ExistingServiceTests
{
	@MockBean
	private DepartmentRepository mockDepartmentRepository;
	
	@InjectMocks
	private ExistingServiceImpl existingService;
	
	@BeforeEach
	void init_mocks()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput()
	{
		when(mockDepartmentRepository.findById(anyString())).thenReturn(Optional.of(new Department("d030", "Test Dept")));
	}
	
	@DisplayName("Test ExistingService with mock repository")
	@Test
	void testGetDepartment()
	{
		Department deptval = new Department("d030", "Test Dept");
		assertEquals(deptval, existingService.getDepartment("d030"));
		
	}
	
}
