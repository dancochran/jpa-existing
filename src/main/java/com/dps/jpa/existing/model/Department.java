package com.dps.jpa.existing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments" )
public class Department
{
	@Id
	@Column(name="dept_no", nullable=false, length=4)
	private String departmentNumber;
	
	@Column(name="dept_name", nullable=false, length=40)
	private String departmentName;

	public Department()
	{
		
	}
	
	public Department(String num, String name)
	{
		this.departmentNumber = num;
		this.departmentName = name;
	}
	
	public String getDepartmentNumber()
	{
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber)
	{
		this.departmentNumber = departmentNumber;
	}
	
	
	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((departmentNumber == null) ? 0 : departmentNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (departmentName == null)
		{
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (departmentNumber == null)
		{
			if (other.departmentNumber != null)
				return false;
		} else if (!departmentNumber.equals(other.departmentNumber))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Department [departmentNumber=" + departmentNumber + ", departmentName=" + departmentName + "]";
	}
	
}
