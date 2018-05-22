package com.hendisantika.springbootquerydsl.service;

import com.hendisantika.springbootquerydsl.model.Department;
import com.hendisantika.springbootquerydsl.model.Employee;
import com.hendisantika.springbootquerydsl.model.QEmployee;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-querydsl
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/05/18
 * Time: 05.19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EmployeeService {

    @Autowired
    EntityManager em;

    public Employee saveEmployee(String name, BigDecimal salary, Department department) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDepartment(department);

        employee = em.merge(employee);
        return employee;
    }

    public Department saveDepartment(String name) {
        Department department = new Department();
        department.setName(name);

        department = em.merge(department);
        return department;
    }

    public List<Employee> getEmployees() {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery query = new JPAQuery(em).from(qEmployee);

        return query.list(qEmployee);
    }

    public List<String> getEmployeeNames() {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery query = new JPAQuery(em).from(qEmployee);

        return query.list(qEmployee.name);
    }

    public Employee getEmployeeById(long id) {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery query = new JPAQuery(em).from(qEmployee);
        query.where(qEmployee.id.eq(id));

        return query.singleResult(qEmployee);
    }

    public List<Employee> getEmployeesWithSalaryBetween(BigDecimal minSalary, BigDecimal maxSalary) {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery query = new JPAQuery(em).from(qEmployee);
        query.where(qEmployee.salary.between(minSalary, maxSalary));

        return query.list(qEmployee);
    }

    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery query = new JPAQuery(em).from(qEmployee);
        query.where(qEmployee.department.name.eq(departmentName));

        return query.list(qEmployee);
    }
}
