package com.example.springbootthymeleafnew.service;

import com.example.springbootthymeleafnew.dto.PasswordDTO;
import com.example.springbootthymeleafnew.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
//    Employee getEmployeeByEmail(String email);
    Employee getEmployeeByEmailAndPassword(String email, String password);
    Employee getEmployeeByEmployeeId(String id);
    void changePassword(Employee loggerUser, PasswordDTO passwordDTO);
}
