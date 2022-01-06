package com.example.springbootthymeleafnew.repository;

import com.example.springbootthymeleafnew.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    Page<Employee> findEmployeeByFirstName(String first);
    Employee getEmployeeByEmail(String email);
    Employee getEmployeeByEmailAndPassword(String email, String password);
    Employee findByEmployeeId(String id);
}
