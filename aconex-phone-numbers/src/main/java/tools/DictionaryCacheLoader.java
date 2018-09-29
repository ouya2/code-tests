package tools;

import com.google.common.io.Resources;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import model.PhoneNumberWordEntry;

public class DictionaryCacheLoader {

  /**
   * The default file name of dictionary when no dictionary is specified.
   */
  private static final String DEFAULT_DICTIONARY_NAME = "dictionary.txt";

  private NumberWordConverter numberWordConverter;


  public DictionaryCacheLoader(NumberWordConverter numberWordConverter) {
    this.numberWordConverter = numberWordConverter;
  }

  /**
   * Load the words of default dictionary into memory
   */
  public List<PhoneNumberWordEntry> cacheDefaultDictionary() {
    return this.cacheDictionary(null);
  }

  /**
   * Load the words of a specific dictionary
   *
   * @param path The path to the dictionary file
   * @return List of number to word mappings
   */
  public List<PhoneNumberWordEntry> cacheDictionary(String path) {
    try {
      List<String> allWords = null;
      if (path == null) {
        allWords = Resources.readLines(Resources.getResource(DEFAULT_DICTIONARY_NAME), Charset.defaultCharset());
      } else {
        Path filePath = Paths.get(path);
        allWords = Files.readAllLines(filePath);
      }

      return allWords.stream()
          .map(aWord -> new PhoneNumberWordEntry(
              numberWordConverter.convertWordToNumber(aWord),
              aWord.toUpperCase()))
          .collect(Collectors.toList());

    } catch (IOException e) {
      System.out.println("Cannot load dictionary: " + e.getLocalizedMessage());
    }
    return Collections.emptyList();
  }
}
