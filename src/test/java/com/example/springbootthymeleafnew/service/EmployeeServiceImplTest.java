package com.example.springbootthymeleafnew.service;

import com.example.springbootthymeleafnew.dto.PasswordDTO;
import com.example.springbootthymeleafnew.model.Employee;
import com.example.springbootthymeleafnew.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeServiceImpl employeeService;
    Employee employee;
    PasswordDTO passwordDTO;

    @BeforeEach
    void setUp() {

        passwordDTO = new PasswordDTO();
        passwordDTO.setNewPassword("3333");
        passwordDTO.setOldPassword("2222");

        employee = new Employee();
        employee.setEmployeeId("0000");
        employee.setFirstName("hamid");
        employee.setLastName("ibrahim");
        employee.setPassword("2222");
        employee.setGender("male");
        employee.setDob("14-10-2020");
        employee.setEmail("haneef@gmail.com");
        employee.setRole("admin");
        employee.setPhoneNumber("08081476273");
    }

    @Test
    void addEmployee() {
        //mock repository
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        employeeService.addEmployee(employee);
        //assertion
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        List<Employee> employeeList =  employeeService.getAllEmployees();
        assertNotNull(employeeList);
        //assertions
        assertEquals("0000", employeeList.get(0).getEmployeeId());
        assertEquals("hamid", employeeList.get(0).getFirstName());
        assertEquals("ibrahim", employeeList.get(0).getLastName());
        assertEquals("2222", employeeList.get(0).getPassword());
    }

    @Test
    void changePassword() {

            when(employeeRepository.getEmployeeByEmailAndPassword(anyString(), anyString())).thenReturn(employee);

            when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

            employeeService.changePassword(employee, passwordDTO);
            assertEquals("3333", passwordDTO.getNewPassword());

            verify(employeeRepository, times(1)).save(any(Employee.class));
            verify(employeeRepository, times(1)).getEmployeeByEmailAndPassword(anyString(), anyString());
    }
}
