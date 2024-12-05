package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	// by native query (MySQL)
	@Query(value = "SELECT * \r\n"
			+ "FROM `hr-employee` emp INNER JOIN `hr-department` dept\r\n"
			+ "ON emp.department_id = dept.dept_id\r\n"
			+ "WHERE emp.department_id = :deptId", nativeQuery = true)
	public List<Employee> findByDepartmentIdNative(@Param("deptId") Long deptId);
	
	// by java query (JPQL)
	@Query("SELECT emp FROM Employee emp JOIN emp.department dept Where dept.id = :deptId")
	public List<Employee> findByDepartmentIdJPQL(@Param("deptId") Long deptId);
	
	// by Named Parameter
	public List<Employee> findByDepartmentId(Long deptId);
	
}