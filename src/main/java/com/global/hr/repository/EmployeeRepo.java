package com.global.hr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;
import com.global.hr.projection.EmployeeProjection;
import com.global.hr.projection.HRStatisticsProjection;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	// Derived / LookUP queries
	public List<Employee> findByNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	public Long countByNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional()
	public Long deleteByNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	// Named queries that defined at top of entity with jpql or native query
	public List<Employee> findBySalary(Double empSalary);
	
	// by native query (MySQL)
	@Query(value = "SELECT * \r\n"
			+ "FROM `hr-employee` emp INNER JOIN `hr-department` dept\r\n"
			+ "ON emp.department_id = dept.dept_id\r\n"
			+ "WHERE emp.department_id = :deptId", nativeQuery = true)
	public List<Employee> findByDepartmentIdNative(@Param("deptId") Long deptId);
	
	
	// by java query (JPQL)
		// interface projection
//		@Query("SELECT emp FROM #{#entityName} emp JOIN emp.department dept Where dept.id = :deptId")
//		public Page<EmployeeProjection> findByDepartmentIdJPQL(@Param("deptId") Long deptId, Pageable pageable);
		// class based projection
		@Query("SELECT new Employee(emp.id, emp.name, emp.salary) FROM #{#entityName} emp JOIN emp.department dept Where dept.id = :deptId")
		public Page<Employee> findByDepartmentIdJPQL(@Param("deptId") Long deptId, Pageable pageable);
	
	// by Derived / LookUP query
	public List<Employee> findByDepartmentId(Long deptId);
	
	// by native query with custom properties that needs using projection interface to map it
	@Query(value = "select \r\n"
			+ "(select count(*) from hr_employee) empCount,\r\n"
			+ "(select count(*) from hr_department) deptCount,\r\n"
			+ "(select count(*) from hr_users) userCount", nativeQuery = true)
	HRStatisticsProjection getHRStatistics();
	
}