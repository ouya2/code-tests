package com.momenton.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OrganisationConverterTest {

  private static final String TEST_FILE = "/employees.csv";

  private OrganisationConverter converter;

  @Before
  public void setup(){
    converter = new OrganisationConverter();
  }

  @Test
  public void testEmptyFile() throws IOException {
    Organisation organisation = converter.toOrganisationFromFile(null);
    assertTrue(organisation.getEmployees().isEmpty());
  }

  @Test
  public void testOrganisationConverterSuccess() throws IOException {
    InputStream inputStream = this.getClass().getResourceAsStream(TEST_FILE);
    Organisation org = converter.toOrganisationFromFile(inputStream);
    List<Employee> employeeList = org.getEmployees();
    assertEquals(6, employeeList.size());
    Employee ceo = org.getEmployeeById(150);
    assertNull(ceo.getManagerId());
    Employee employee = org.getEmployeeById(275);
    assertEquals("Alex", employee.getName());
    assertEquals(Integer.valueOf(275), employee.getId());
  }

}
