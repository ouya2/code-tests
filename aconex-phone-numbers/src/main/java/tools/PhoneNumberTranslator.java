package tools;

import application.Translatable;
import java.util.List;
import model.PhoneNumberWordEntry;

public class PhoneNumberTranslator implements Translatable {

  private NumberWordConverter numberWordConverter;

  public PhoneNumberTranslator(NumberWordConverter numberWordConverter) {
    this.numberWordConverter = numberWordConverter;
  }

  @Override
  public String translateOneEntry(String entityString, List<PhoneNumberWordEntry> entries) {
    return null;
  }

  @Override
  public List<String> translateEntries(String entitiesFilePath, List<PhoneNumberWordEntry> entries) {
    return null;
  }
}
