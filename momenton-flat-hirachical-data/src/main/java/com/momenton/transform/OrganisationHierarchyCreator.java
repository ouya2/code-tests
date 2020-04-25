package com.momenton.transform;

import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create the organisation as a hierarchical structure.
 */
public class OrganisationHierarchyCreator {

  public static final String TAB = "\t";
  public static final String DASH = "-";
  public static final String SPACE = " ";

  private final List<Employee> employees;

  public OrganisationHierarchyCreator(Organisation organisation) {
    this.employees = organisation.getEmployees();
  }

  public String generateOrganisationHierarchy() {
    Optional<Employee> ceo = employees.stream()
        .filter(employee -> employee.getManagerId() == null)
        .findFirst();
    ceo.ifPresent(this::buildOrganisationHierarchy);

    int tab = 0;
    StringBuilder hierarchyTextBuilder = new StringBuilder();
    ceo.ifPresent(employee -> toHierarchyText(employee, tab, hierarchyTextBuilder));
    return hierarchyTextBuilder.substring(0, hierarchyTextBuilder.length());
  }

  private void buildOrganisationHierarchy(Employee employee) {
    List<Employee> managedEmployees =
        employees.stream()
        .filter(oneEmployee -> employee.getId().equals(oneEmployee.getManagerId()))
        .collect(Collectors.toList());
    employee.setSubEmployees(managedEmployees);
    if (managedEmployees.isEmpty()) {
      return;
    }

    managedEmployees.forEach(this::buildOrganisationHierarchy);
  }

  private void toHierarchyText(Employee employee, int numOfTabs, StringBuilder stringBuilder) {
    for (int i = 0; i < numOfTabs; i++) {
      stringBuilder.append(TAB);
    }
    stringBuilder.append(DASH)
        .append(employee.getName())
        .append(System.lineSeparator());

    List<Employee> subEmployees = employee.getSubEmployees();
    stringBuilder.append(SPACE);
    for (Employee e : subEmployees) {
      toHierarchyText(e, numOfTabs + 1, stringBuilder);
    }
  }
}
