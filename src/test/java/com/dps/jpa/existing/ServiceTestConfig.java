package com.dps.jpa.existing;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.dps.jpa.existing.repository.DepartmentRepository;

@Profile("servicetest")
@Configuration
public class ServiceTestConfig
{
	@Bean
	@Primary
	public DepartmentRepository departmentRepository()
	{
		return Mockito.mock(DepartmentRepository.class);
	}
}
