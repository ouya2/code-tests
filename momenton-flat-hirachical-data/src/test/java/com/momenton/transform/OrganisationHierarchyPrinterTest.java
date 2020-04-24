package com.momenton.transform;

import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import org.junit.Test;

public class OrganisationHierarchyPrinterTest {

  @Test
  public void testDisplayOrganisationHierarchySuccess() {
    OrganisationHierarchyPrinter printer = new OrganisationHierarchyPrinter(getOrganisation());
    printer.displayOrganisationHierachy();
  }

  private Organisation getOrganisation() {
    Organisation org = new Organisation();
    org.addEmployee(new Employee("Jamie", 150));
    Employee firstEmployee = new Employee("Alan", 100);
    firstEmployee.setManagerId(150);
    org.addEmployee(firstEmployee);
    Employee secondEmployee = new Employee("Martin", 220);
    secondEmployee.setManagerId(100);
    org.addEmployee(secondEmployee);
    Employee thirdEmployee = new Employee("Alex", 275);
    thirdEmployee.setManagerId(100);
    org.addEmployee(thirdEmployee);
    Employee fourthEmployee = new Employee("Steve", 400);
    fourthEmployee.setManagerId(150);
    org.addEmployee(fourthEmployee);
    Employee fifthEmployee = new Employee("David", 190);
    fifthEmployee.setManagerId(400);
    org.addEmployee(fifthEmployee);
    return org;
  }

}
