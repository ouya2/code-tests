package application;

import java.util.List;
import model.PhoneNumberWordEntry;

/**
 * Abstract definition of a translatable entity.
 */
public interface Translatable {

  /**
   * Translate one entity into a certain string
   *
   * @param entityString
   * @return
   */
  String translateOneEntry(String entityString, List<PhoneNumberWordEntry> mappingEntries);

  /**
   *
   * @param entitiesFilePath
   * @return
   */
  List<String> translateEntries(String entitiesFilePath, List<PhoneNumberWordEntry> mappingEntries);
}
