package tools;

import static application.AppConstants.LETTER_NUMBER_MAP;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.ListUtils;

/**
 * The converter to get word from number and vice versa.
 */
public final class NumberWordConverter {

  /**
   * Convert a word to a number
   *
   * @param word Given word
   * @return the number
   */
  public String convertWordToNumber(String word) {
    StringBuilder sb = new StringBuilder(word.length());
    Lists.charactersOf(word.toUpperCase())
         .listIterator()
         .forEachRemaining(eachChar ->
                 sb.append(LETTER_NUMBER_MAP.get(eachChar)));
    return sb.toString();
  }

  /**
   *
   * @param number
   * @return
   */
  public List<String> convertNumberToWords(Integer number, final Map<Integer, List<String>> numberWordsMap) {
    return ListUtils.emptyIfNull(numberWordsMap.get(number));
  }

}
