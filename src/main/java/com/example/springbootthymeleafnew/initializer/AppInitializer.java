package com.example.springbootthymeleafnew.initializer;

import com.example.springbootthymeleafnew.model.Employee;
import com.example.springbootthymeleafnew.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {


    private EmployeeRepository employeeRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Employee employee = new Employee(1L, "001", "hamid", "ibrahim",
                "14-10-2020", "hamid@gmail.com", "08081476273", "hamid@gmail.com", "male", "admin");
        employeeRepository.save(employee);
    }
}
