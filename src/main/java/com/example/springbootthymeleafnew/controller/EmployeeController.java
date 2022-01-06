package com.example.springbootthymeleafnew.controller;

import com.example.springbootthymeleafnew.dto.PasswordDTO;
import com.example.springbootthymeleafnew.model.Employee;
import com.example.springbootthymeleafnew.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //display list of employees
    @GetMapping("/home")
    public String ViewHomePage(Model model) {

        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "employee";
    };


    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model, HttpServletRequest request) {
        //create model attribute to bind form data
        HttpSession session = request.getSession();

        Employee employee1 = (Employee) session.getAttribute("loggerUSer");
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        session.setAttribute("loggerUser", employee1);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request){
        //save employee to database

        HttpSession session = request.getSession();

        Employee employee1 = (Employee) session.getAttribute("loggerUSer");
        employeeService.addEmployee(employee);
        session.setAttribute("loggerUser", employee1);
        return "redirect:/home";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        //set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/changePasswordForm/{id}")
    public String changePasswordForm(@PathVariable (value = "id") String id, HttpSession httpSession, Model model){
        //get employee from the service
        Employee employee = employeeService.getEmployeeByEmployeeId(id);
        httpSession.setAttribute("newUser", employee);
        //set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", new PasswordDTO());
        return "change-password";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("employee") PasswordDTO passwordDTO, HttpServletRequest request){

        //save employee to database
        Employee loggerUser = (Employee) request.getSession().getAttribute("newUser");
        this.employeeService.changePassword(loggerUser, passwordDTO);

        return "redirect:/get-home";
    }


    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){

        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/home";
    }

}
