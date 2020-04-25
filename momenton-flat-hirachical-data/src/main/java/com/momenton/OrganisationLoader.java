package com.momenton;

import com.momenton.model.Organisation;
import com.momenton.transform.OrganisationConverter;
import com.momenton.transform.OrganisationHierarchyCreator;
import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Application Launcher
 */
public class OrganisationLoader {

  public static void main(String[] args) {

    Options options = new Options();
    options.addOption("f", false,
        "Please specify csv file to load organisation data.");

    try {
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);

      String csvFile;
      if (!cmd.hasOption("f")) {
        System.out.println("CSV file not specified, use default file: ");
        csvFile = OrganisationLoader.class.getResource("/employees.csv").getFile();
      }
      else {
        csvFile = cmd.getOptionValue("f");
      }
      displayOrganisationHierarchy(csvFile);
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void displayOrganisationHierarchy(String csvFile) throws IOException {
    OrganisationConverter converter = new OrganisationConverter();
    Organisation organisation = converter.toOrganisationFromFile(csvFile);
    OrganisationHierarchyCreator hierarchyCreator = new OrganisationHierarchyCreator(organisation);
    System.out.println(hierarchyCreator.generateOrganisationHierarchy());
  }
}
