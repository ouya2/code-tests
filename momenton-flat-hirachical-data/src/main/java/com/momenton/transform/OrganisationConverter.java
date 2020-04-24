package com.momenton.transform;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.momenton.model.Employee;
import com.momenton.model.Organisation;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class OrganisationConverter {

  public static final String EMPLOYEE_NAME_HEADER = "Employee Name";
  public static final String ID_HEADER = "id";
  public static final String MANAGER_ID_HEADER = "Manager id";

  public Organisation toOrganisationFromFile(InputStream fileInputStream) throws IOException {
    Organisation organisation = new Organisation();
    if (fileInputStream != null) {
      CSVParser csvParser = CSVFormat.EXCEL
          .withFirstRecordAsHeader().parse(new InputStreamReader(fileInputStream));
      List<CSVRecord> records = csvParser.getRecords();
      records.forEach(record -> organisation.addEmployee(createEmployee(record)));
    }
    return organisation;
  }

  private Employee createEmployee(CSVRecord record) {
    String name = record.get(EMPLOYEE_NAME_HEADER);
    String id = record.get(ID_HEADER);
    String managerId = record.get(MANAGER_ID_HEADER);
    Employee employee = new Employee(name, Integer.valueOf(id));
    if (isNotEmpty(managerId)) {
      employee.setManagerId(Integer.valueOf(managerId));
    }
    return employee;
  }

}
