package com.example.springbootthymeleafnew.service;

import com.example.springbootthymeleafnew.dto.PasswordDTO;
import com.example.springbootthymeleafnew.model.Employee;
import com.example.springbootthymeleafnew.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        System.err.println(employees);
        return employees;
    }

    @Override
   public void addEmployee(Employee employee) {
       employeeRepository.save(employee);
   }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }
    @Override
    public Employee getEmployeeByEmployeeId(String id) {
        return employeeRepository.findByEmployeeId(id);
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }


    @Override
    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        Employee employee = employeeRepository.getEmployeeByEmailAndPassword(email, password);
        if(employee == null){
            System.out.println("Password or Email Error!");
            return null;
        }
        return employee;
    }

    @Override
    public void changePassword(Employee loggerUser, PasswordDTO passwordDTO) {

        if(loggerUser.getPassword().equals(passwordDTO.getOldPassword())){
            Employee employee = employeeRepository.getEmployeeByEmailAndPassword(loggerUser.getEmail(),
                    loggerUser.getPassword());

            employee.setId(employee.getId());
            employee.setPassword(passwordDTO.getNewPassword());

            employeeRepository.save(employee);

        }

    }
}
