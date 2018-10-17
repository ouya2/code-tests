package test.com.company.onlinestore;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acme.serviceavailability.AvailabilityChecker;
import com.acme.serviceavailability.TechnicalFailureException;
import com.company.onlinestore.Basket;
import com.company.onlinestore.ThreeDeeAddOnService;
import com.company.onlinestore.ThreeDeeAddOnServiceImpl;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ThreeDeeAddOnServiceImplTest {

  @Mock
  AvailabilityChecker checker;

  @Mock
  Basket basket;

  private ThreeDeeAddOnService service;

  @Before
  public void setup() {
    service = new ThreeDeeAddOnServiceImpl(checker);
  }

  @Test
  public void testCheckFor3DAddOnProducts_InvalidPostCode() throws TechnicalFailureException {
    when(checker.isPostCodeIn3DTVServiceArea("4567")).thenThrow(TechnicalFailureException.class);
    service.checkFor3DAddOnProducts(basket, "4567");
    verify(basket, times(0)).getThreeDeeCompatibleProducts();
  }

  @Test
  public void testCheckFor3DAddOnProducts_SERVICE_AVAILABLE() throws TechnicalFailureException {
    when(checker.isPostCodeIn3DTVServiceArea("4567")).thenReturn("SERVICE_AVAILABLE");
    when(basket.getThreeDeeCompatibleProducts()).thenReturn(Arrays.asList("SPORTS", "NEWS", "MOVIE"));
    when(basket.generateNotification(anyList())).thenReturn("All relevant addOns: SPORTS_3D_ADD_ON, NEWS_3D_ADD_ON, MOVIES_3D_ADD_ON");

    service.checkFor3DAddOnProducts(basket, "4567");
    verify(checker, times(1)).isPostCodeIn3DTVServiceArea("4567");
    verify(basket, times(1)).generateNotification(anyList());
  }

  @Test
  public void testCheckFor3DAddOnProducts_SERVICE_PLANNED() throws TechnicalFailureException {
    when(checker.isPostCodeIn3DTVServiceArea("1235")).thenReturn("SERVICE_PLANNED");

    service.checkFor3DAddOnProducts(basket, "1235");
    verify(checker, times(1)).isPostCodeIn3DTVServiceArea("1235");
  }

  @Test
  public void testCheckFor3DAddOnProducts_SERVICE_AVAILABLE_EMPTY_BASKET() throws TechnicalFailureException {
    when(checker.isPostCodeIn3DTVServiceArea("4567")).thenReturn("SERVICE_AVAILABLE");
    when(basket.getThreeDeeCompatibleProducts()).thenReturn(Collections.emptyList());
    when(basket.generateNotification(Collections.emptyList())).thenReturn("No 3d AddOns");

    service.checkFor3DAddOnProducts(basket, "4567");
    verify(checker, times(1)).isPostCodeIn3DTVServiceArea("4567");
    verify(basket, times(1)).generateNotification(anyList());
  }

  @Test
  public void testCheckFor3DAddOnProducts_SERVICE_UNAVAILABLE_FULL_BASET() throws TechnicalFailureException {
    when(checker.isPostCodeIn3DTVServiceArea("4567")).thenReturn("SERVICE_UNAVAILABLE");
    when(basket.getThreeDeeCompatibleProducts()).thenReturn(Arrays.asList("SPORTS", "NEWS", "MOVIE"));
    service.checkFor3DAddOnProducts(basket, "4567");
    verify(checker, times(1)).isPostCodeIn3DTVServiceArea("4567");
    verify(basket, times(0)).generateNotification(anyList());
  }
}
