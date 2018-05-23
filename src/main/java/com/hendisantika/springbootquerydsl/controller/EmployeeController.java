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

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public Department createDepartment(@RequestBody Department department) {
        return employeeService.saveDepartment(department.getName());
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @RequestMapping(value = "/employeeNames", method = RequestMethod.GET)
    public List<String> getEmployeeNames() {
        return employeeService.getEmployeeNames();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(value = "/employees/by_salary", method = RequestMethod.GET)
    public List<Employee> getEmployeesBySalary(@RequestParam BigDecimal minSalary, @RequestParam BigDecimal maxSalary) {
        return employeeService.getEmployeesWithSalaryBetween(minSalary, maxSalary);
    }

    @RequestMapping(value = "/employees/by_department_name", method = RequestMethod.GET)
    public List<Employee> getEmployeesByDepartmentName(@RequestParam String name) {
        return employeeService.getEmployeesByDepartmentName(name);
    }
}