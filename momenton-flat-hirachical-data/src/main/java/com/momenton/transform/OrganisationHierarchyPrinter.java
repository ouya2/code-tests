package com.momenton.transform;

import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Print out the organisation as a hierarchical structure.
 */
public class OrganisationHierarchyPrinter {

  private final List<Employee> employees;

  public OrganisationHierarchyPrinter(Organisation organisation) {
    this.employees = organisation.getEmployees();
  }

  public void displayOrganisationHierachy() {
    Optional<Employee> ceo = employees.stream()
        .filter(employee -> employee.getManagerId() == null)
        .findFirst();
    ceo.ifPresent(this::buildOrganisationHierachy);

    int tab = 0;
    ceo.ifPresent(employee -> printOrganisation(employee, tab));
  }

  private void buildOrganisationHierachy(Employee employee) {
    List<Employee> managedEmployees =
        employees.stream()
        .filter(oneEmployee -> employee.getId().equals(oneEmployee.getManagerId()))
        .collect(Collectors.toList());
    employee.setSubEmployees(managedEmployees);
    if (managedEmployees.isEmpty()) {
      return;
    }

    managedEmployees.forEach(this::buildOrganisationHierachy);
  }

  private void printOrganisation(Employee employee, int numOfTabs) {
    for (int i = 0; i < numOfTabs; i++) {
      System.out.print("\t");
    }
    System.out.println("-" + employee.getName());
    List<Employee> subEmployees = employee.getSubEmployees();
    System.out.print(" ");
    for (Employee e : subEmployees) {
      printOrganisation(e, numOfTabs + 1);
    }
  }
}
