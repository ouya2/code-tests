package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import model.PhoneNumberWordEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tools.DictionaryCacheLoader;

@RunWith(MockitoJUnitRunner.class)
public class AppLauncherTest {

  private static final String DICTIONARY = "/resource/dictionary.txt";
  private static final String PHONE_NUMBERS = "/input/phoneNumbers.txt";

  @Mock
  private Translatable translator;

  @Mock
  private DictionaryCacheLoader dictionaryLoader;

  private AppLauncher launcher;
  private List<PhoneNumberWordEntry> entries;

  @Before
  public void setup() {
    launcher = new AppLauncher(translator, dictionaryLoader);
    entries = Arrays.asList(new PhoneNumberWordEntry("2255", "CALL"));
    when(dictionaryLoader.cacheDefaultDictionary()).thenReturn(entries);
    when(dictionaryLoader.cacheDictionary(DICTIONARY)).thenReturn(entries);
  }

  @Test
  public void testTranslateWithParameters() {
    when(translator.translateEntries(PHONE_NUMBERS, entries)).thenReturn(Arrays.asList("1-800-CALL"));
    List<String> results = launcher.startDecoderWithParameters(new String[]{"-d",DICTIONARY, "-p", PHONE_NUMBERS});
    assertEquals(1, results.size());
    assertEquals("1-800-CALL", results.get(0));
  }

  @Test
  public void testTranslateWithParametersOnlyPhoneNumberFile() {
    when(translator.translateEntries(PHONE_NUMBERS, entries)).thenReturn(Arrays.asList("1-800-CALL", "CALL-AGAIN"));

    List<String> results = launcher.startDecoderWithParameters(new String[]{"-p", PHONE_NUMBERS});
    assertEquals(2, results.size());
    assertEquals("1-800-CALL", results.get(0));
    assertEquals("CALL-AGAIN", results.get(1));
  }

  @Test
  public void testTranslateWithParametersNoFileSpecified() {
    List<String> results = launcher.startDecoderWithParameters(new String[]{});
    assertTrue(results.isEmpty());
  }
}
