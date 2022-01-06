package com.example.springbootthymeleafnew.controller;

import com.example.springbootthymeleafnew.model.Employee;
import com.example.springbootthymeleafnew.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    private final EmployeeService employeeService;

    @Autowired
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String getLoginPage(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("invalid", null);
        model.addAttribute("newRegistration", null);
        return "login";
    }

    @GetMapping("/get-home")
    public String getHomePage( Model model){

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        var employee = (Employee) session.getAttribute("user");
        log.error("{}", employee);
        model.addAttribute("loggerUser", employee);
        boolean isRoleEmp = employee.getRole().equalsIgnoreCase("admin") ? true : false;
        model.addAttribute("role", isRoleEmp);

        return "index";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("user") Employee employee, HttpServletRequest request, Model model) {
        Employee employee1 = employeeService.getEmployeeByEmailAndPassword(employee.getEmail(), employee.getPassword());

        HttpSession session = request.getSession();
        if(employee1 != null) {
            session.setAttribute("user", employee1);
            model.addAttribute("loggerUser", employee1);
            boolean isRoleEmp = employee1.getRole().equalsIgnoreCase("admin") ? true : false;
            model.addAttribute("role", isRoleEmp);
            return "index";
        }
        return "redirect:/";
    }


}
