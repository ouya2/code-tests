package com.momenton.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.momenton.exception.IllegalCSVFileException;
import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OrganisationConverterTest {

  private static final String TEST_FILE = "/employees.csv";
  private static final String INVALID_CSV_WITHOUT_ID = "/invalidEmployeesWithoutId.csv";
  private static final String INVALID_CSV_WITHOUT_NAME = "/invalidEmployeesWithoutName.csv";
  private static final String INVALID_CSV_WITH_MULTIPLE_CEOS = "/invalidEmployeesWithMultipleCEOs.csv";
  private static final String INVALID_CSV_WITHOUT_CEO = "/invalidEmployeesWithoutCEO.csv";

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
    String testFile = this.getClass().getResource(TEST_FILE).getFile();
    Organisation org = converter.toOrganisationFromFile(testFile);
    List<Employee> employeeList = org.getEmployees();
    assertEquals(6, employeeList.size());
    Employee ceo = org.getEmployeeById(150);
    assertNull(ceo.getManagerId());
    Employee employee = org.getEmployeeById(275);
    assertEquals("Alex", employee.getName());
    assertEquals(Integer.valueOf(275), employee.getId());
  }

  @Test (expected = IllegalCSVFileException.class)
  public void testInvalidCSVRecordWithoutId() throws IOException {
    String testFile = this.getClass().getResource(INVALID_CSV_WITHOUT_ID).getFile();
    converter.toOrganisationFromFile(testFile);
  }

  @Test (expected = IllegalCSVFileException.class)
  public void testInvalidCSVRecordWithoutName() throws IOException {
    String testFile = this.getClass().getResource(INVALID_CSV_WITHOUT_NAME).getFile();
    converter.toOrganisationFromFile(testFile);
  }

  @Test (expected = IllegalCSVFileException.class)
  public void testMultipleCEOCSVRecordFailure() throws IOException {
    String testFile = this.getClass().getResource(INVALID_CSV_WITH_MULTIPLE_CEOS).getFile();
    converter.toOrganisationFromFile(testFile);
  }

  @Test (expected = IllegalCSVFileException.class)
  public void testCSVRecordWithoutCEOFailure() throws IOException {
    String testFile = this.getClass().getResource(INVALID_CSV_WITHOUT_CEO).getFile();
    converter.toOrganisationFromFile(testFile);
  }
}
