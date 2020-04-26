package com.momenton.transform;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.momenton.exception.IllegalCSVFileException;
import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parse the csv file and convert to <code>Organisation</code> object with list of <code>Employee</code>
 */
public class OrganisationConverter {

  private static final Logger logger = LoggerFactory.getLogger(OrganisationConverter.class);

  public static final String EMPLOYEE_NAME_HEADER = "Employee Name";
  public static final String ID_HEADER = "id";
  public static final String MANAGER_ID_HEADER = "Manager id";

  public Organisation toOrganisationFromFile(String csvFile) throws IOException {
    Organisation organisation = new Organisation();
    if (isNotEmpty(csvFile)) {
      BufferedReader reader = new BufferedReader(new FileReader(csvFile));
      CSVParser csvParser = CSVFormat.EXCEL
          .withFirstRecordAsHeader().parse(reader);
      List<CSVRecord> records = csvParser.getRecords();
      records.forEach(record -> organisation.addEmployee(createEmployee(record)));
      List<Employee> topManagers = organisation.getEmployees()
          .stream()
          .filter(employee -> employee.getManagerId() == null)
          .collect(Collectors.toList());
      if (topManagers.size() != 1) {
        logger.error("Expected one CEO only.");
        throw new IllegalCSVFileException("CSV record is invalid");
      }
    }
    return organisation;
  }

  private Employee createEmployee(CSVRecord record) {
    String name = record.get(EMPLOYEE_NAME_HEADER);
    String id = record.get(ID_HEADER);
    String managerId = record.get(MANAGER_ID_HEADER);
    if (isEmpty(id)) {
      logger.error("Employee id is not set in {}", record);
      throw new IllegalCSVFileException("CSV record is invalid");
    }
    if (isEmpty(name)) {
      logger.error("Employee name is not set in {}", record);
      throw new IllegalCSVFileException("CSV record is invalid");
    }
    Employee employee = new Employee(name, Integer.valueOf(id));
    if (isNotEmpty(managerId)) {
      employee.setManagerId(Integer.valueOf(managerId));
    }
    return employee;
  }
}
