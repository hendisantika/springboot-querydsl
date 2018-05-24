package com.hendisantika.springbootquerydsl.controller;

import com.hendisantika.springbootquerydsl.model.Department;
import com.hendisantika.springbootquerydsl.model.Employee;
import com.hendisantika.springbootquerydsl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-querydsl
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/05/18
 * Time: 06.44
 * To change this template use File | Settings | File Templates.
 */
@RestController
@Transactional
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    @GetMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return employeeService.saveDepartment(department.getName());
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employeeNames")
    public List<String> getEmployeeNames() {
        return employeeService.getEmployeeNames();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees/by_salary")
    public List<Employee> getEmployeesBySalary(@RequestParam BigDecimal minSalary, @RequestParam BigDecimal maxSalary) {
        return employeeService.getEmployeesWithSalaryBetween(minSalary, maxSalary);
    }

    @GetMapping("/employees/by_department_name")
    public List<Employee> getEmployeesByDepartmentName(@RequestParam String name) {
        return employeeService.getEmployeesByDepartmentName(name);
    }
}