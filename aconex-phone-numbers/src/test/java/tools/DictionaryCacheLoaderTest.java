package tools;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.List;
import model.PhoneNumberWordEntry;
import org.junit.Before;
import org.junit.Test;

public class DictionaryCacheLoaderTest {

  private NumberWordConverter converter = new NumberWordConverter();

  private DictionaryCacheLoader dictionaryCacheLoader;

  @Before
  public void setup() {
    dictionaryCacheLoader = new DictionaryCacheLoader(converter);
  }

  @Test
  public void testLoadDefaultDictionary() {
    List<PhoneNumberWordEntry> cache = dictionaryCacheLoader.cacheDefaultDictionary();
    assertFalse(cache.isEmpty());
    assertEquals(84096, cache.size());
  }

  @Test
  public void testCacheDictionary() {
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("test.txt").getFile());
    List<PhoneNumberWordEntry> cache = dictionaryCacheLoader.cacheDictionary(
        file.getAbsolutePath());
    assertFalse(cache.isEmpty());
  }
}
