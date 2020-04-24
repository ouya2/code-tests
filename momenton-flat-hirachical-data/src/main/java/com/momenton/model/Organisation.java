package com.momenton.model;

import java.util.ArrayList;
import java.util.List;

public class Organisation {

  private final List<Employee> employees;

  public Organisation() {
    employees = new ArrayList<>();
  }

  public void addEmployee(Employee employee) {
    this.employees.add(employee);
  }

  public List<Employee> getEmployees() {
    return this.employees;
  }

  public Employee getEmployeeById(Integer id) {
    return employees.get(id);
  }
}
