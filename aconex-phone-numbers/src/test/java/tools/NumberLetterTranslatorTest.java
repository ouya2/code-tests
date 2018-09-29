package tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberLetterTranslatorTest {

  private Map<Integer, List<String>> aMap;

  private NumberWordConverter converter;

  @Before
  public void setup(){
    aMap = new HashMap<>();
    aMap.put(11223, Arrays.asList("abcrt", "aaaji"));
    aMap.put(2234, Arrays.asList("ytgf", "ftre"));
    aMap.put(2255, Arrays.asList("call"));
    converter = new NumberWordConverter();
  }

  @After
  public void tearDown() {

  }

  @Test
  public void testConvertNumberToWords() {
    List<String> words = converter.convertNumberToWords(2255, aMap);
    assertEquals(1, words.size());
    assertEquals("call", words.get(0));
    words = converter.convertNumberToWords(11223, aMap);
    assertEquals(2, words.size());
    assertTrue(words.contains("abcrt"));
  }

  @Test
  public void testConvertWordToNumber() {
    String number = converter.convertWordToNumber("call");
    assertEquals("2255", number);
    number = converter.convertWordToNumber("abcd");
    assertEquals("2223", number);
  }
}
