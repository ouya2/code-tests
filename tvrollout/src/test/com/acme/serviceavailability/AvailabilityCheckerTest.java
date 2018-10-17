package test.com.acme.serviceavailability;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acme.serviceavailability.AvailabilityChecker;
import com.acme.serviceavailability.TechnicalFailureException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityCheckerTest {

  @Mock
  ThreeDeeTVService threeDeeTVService;

  @Test(expected = TechnicalFailureException.class)
  public void testThrowTechnicalFailureException() throws TechnicalFailureException {
    when(threeDeeTVService.getServiceAvailability("1234")).thenThrow(TechnicalFailureException.class);

    AvailabilityChecker checker = new AvailabilityCheckerImpl(threeDeeTVService);
    checker.isPostCodeIn3DTVServiceArea("1234");
  }

  @Test
  public void testServiceAvailable() throws TechnicalFailureException {
    when(threeDeeTVService.getServiceAvailability("1234")).thenReturn("SERVICE_AVAILABLE");

    AvailabilityChecker checker = new AvailabilityCheckerImpl(threeDeeTVService);
    checker.isPostCodeIn3DTVServiceArea("1234");
    verify(threeDeeTVService, times(1)).getServiceAvailability("1234");
  }

}
