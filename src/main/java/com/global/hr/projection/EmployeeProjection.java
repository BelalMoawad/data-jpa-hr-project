package com.global.hr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
	Long getId();
	String getName();
	Double getSalary();
	@Value("#{target.name + '@' + target.id}")
	String getNameWithId();
}
