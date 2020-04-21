package application;

import static application.AppConstants.COMMAND_LINE_DICTIONARY_OPTION;
import static application.AppConstants.COMMAND_LINE_PHONE_NUMBER_OPTION;
import static java.lang.System.exit;

import java.io.Console;
import java.util.Collections;
import java.util.List;
import model.PhoneNumberWordEntry;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import tools.DictionaryCacheLoader;
import tools.NumberWordConverter;
import tools.PhoneNumberTranslator;

/**
 * The application launcher
 */
public class AppLauncher {

  private Translatable phoneNumberTranslator;
  private DictionaryCacheLoader dictionaryLoader;

  public AppLauncher(Translatable phoneNumberTranslator, DictionaryCacheLoader dictionaryCacheLoader) {
    this.phoneNumberTranslator = phoneNumberTranslator;
    this.dictionaryLoader = dictionaryCacheLoader;
  }

  public static void main(String[] args) {
    NumberWordConverter numberWordConverter = new NumberWordConverter();
    AppLauncher launcher = new AppLauncher(new PhoneNumberTranslator(numberWordConverter),
        new DictionaryCacheLoader(numberWordConverter));
    if (args == null || args.length == 0) {
      launcher.startDecoderByDefault();
    } else {
      List<String> results = launcher.startDecoderWithParameters(args);
      results.stream().forEach(System.out::println);
    }
  }

  public List<String> startDecoderWithParameters(String[] args) {
    List<String> results = Collections.emptyList();
    try {
      CommandLineParser commandLineParser = new DefaultParser();
      CommandLine commandLine = commandLineParser.parse(createCommandLineOptions(), args);
      String phoneNumberFile;
      if (commandLine.hasOption(COMMAND_LINE_PHONE_NUMBER_OPTION)) {
        phoneNumberFile = commandLine.getOptionValue(COMMAND_LINE_PHONE_NUMBER_OPTION);
      } else {
        System.err.println("Phone number file must be specified");
        return results;
      }

      List<PhoneNumberWordEntry> phoneNumberWordEntries;
      if (commandLine.hasOption(COMMAND_LINE_DICTIONARY_OPTION)) {
        String dictionaryOptionValue = commandLine.getOptionValue(COMMAND_LINE_DICTIONARY_OPTION);
        phoneNumberWordEntries = dictionaryLoader.cacheDictionary(dictionaryOptionValue);
      }
      else {
        phoneNumberWordEntries = dictionaryLoader.cacheDefaultDictionary();
      }

      results = this.phoneNumberTranslator.translateEntries(phoneNumberFile, phoneNumberWordEntries);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }

    return results;
  }

  public void startDecoderByDefault() {
    Console inputReader = System.console();
    if (inputReader == null) {
      System.err.println("Input reader does not exist.");
      exit(1);
    }

    System.out.println("Enter the phone number or 'exit' it");
    List<PhoneNumberWordEntry> phoneNumberWordEntries = dictionaryLoader.cacheDefaultDictionary();
    boolean isRunning = true;
    while (isRunning) {
      String inputPhoneNumber = inputReader.readLine(": ");
      if ("exit".equalsIgnoreCase(inputPhoneNumber)) {
        isRunning = false;
        exit(1);
      }
      else {
        List<String> results = phoneNumberTranslator.translateOneEntry(inputPhoneNumber, phoneNumberWordEntries);
        results.stream().forEach(System.out::println);
      }
    }
  }

  private Options createCommandLineOptions() {
    Option dictionaryPathCommand = Option.builder(COMMAND_LINE_DICTIONARY_OPTION)
        .required(false)
        .hasArg()
        .desc("Dictionary Path")
        .build();

    Option phoneNumberCommand = Option.builder(COMMAND_LINE_PHONE_NUMBER_OPTION)
        .required(true)
        .hasArg()
        .desc("Phone numbers input file")
        .build();

    Options options = new Options();
    options.addOption(dictionaryPathCommand);
    options.addOption(phoneNumberCommand);
    return options;
  }
}
