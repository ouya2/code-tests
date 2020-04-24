package com.momenton.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

  private String name;
  private Integer managerId;
  private Integer id;
  private List<Employee> subEmployees = new ArrayList<Employee>(2);

  public Employee(String name, Integer id) {
    this.name = name;
    this.id = id;
  }

  public List<Employee> getSubEmployees() {
    return subEmployees;
  }

  public void setSubEmployees(List<Employee> subEmployees) {
    this.subEmployees = subEmployees;
  }

  public void addManagedEmployee(Employee employee) {
    this.subEmployees.add(employee);
  }

  public void setManagerId(Integer managerId) {
    this.managerId = managerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getManagerId() {
    return managerId;
  }
}
