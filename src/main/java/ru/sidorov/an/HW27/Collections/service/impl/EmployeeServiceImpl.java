package ru.sidorov.an.HW27.Collections.service.impl;

import org.springframework.stereotype.Service;
import ru.sidorov.an.HW27.Collections.exception.EmployeeAlreadyAddedException;
import ru.sidorov.an.HW27.Collections.exception.EmployeeNotFoundException;
import ru.sidorov.an.HW27.Collections.exception.EmployeeStorgeIsFullException;
import ru.sidorov.an.HW27.Collections.model.Employee;
import ru.sidorov.an.HW27.Collections.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int EMPLOYEE_STORAGE_SIZE = 10;
    private final Map<String, Employee> employees = new HashMap<>();
    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() == EMPLOYEE_STORAGE_SIZE){
            throw new EmployeeStorgeIsFullException();
        }

        Employee employee = new Employee(firstName, lastName);
        //реализация Григория
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> findAll() {

        return employees.values();
    }
}
