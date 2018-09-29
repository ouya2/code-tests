package application;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public final class AppConstants {

  /**
   * The regular expression for punctuation
   */
  public static final String PUNCTUATION_REGEX = "[.,:;]";

  /**
   * The command line option to specify dictionary path
   */
  public static final String COMMAND_LINE_DICTIONARY_OPTION = "d";

  /**
   * The command line option to specify phone number path
   */
  public static final String COMMAND_LINE_PHONE_NUMBER_OPTION = "p";

  /**
   * The letter and phone number digit mapping
   */
  public static final Map<Character, Integer> LETTER_NUMBER_MAP = ImmutableMap.<Character, Integer>builder()
      .put('A', Integer.valueOf(2))
      .put('B', Integer.valueOf(2))
      .put('C', Integer.valueOf(2))
      .put('D', Integer.valueOf(3))
      .put('E', Integer.valueOf(3))
      .put('F', Integer.valueOf(3))
      .put('G', Integer.valueOf(4))
      .put('H', Integer.valueOf(4))
      .put('I', Integer.valueOf(4))
      .put('J', Integer.valueOf(5))
      .put('K', Integer.valueOf(5))
      .put('L', Integer.valueOf(5))
      .put('M', Integer.valueOf(6))
      .put('N', Integer.valueOf(6))
      .put('O', Integer.valueOf(6))
      .put('P', Integer.valueOf(7))
      .put('Q', Integer.valueOf(7))
      .put('R', Integer.valueOf(7))
      .put('S', Integer.valueOf(7))
      .put('T', Integer.valueOf(8))
      .put('U', Integer.valueOf(8))
      .put('V', Integer.valueOf(8))
      .put('W', Integer.valueOf(9))
      .put('X', Integer.valueOf(9))
      .put('Y', Integer.valueOf(9))
      .put('Z', Integer.valueOf(9))
      .build();
}
