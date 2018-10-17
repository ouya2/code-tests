package test.com.company.onlinestore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.company.onlinestore.Basket;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

public class BasketTest {


  @Test
  public void testIncompatibleProducts() {
    Basket basket = new Basket(Arrays.asList("KIDS, VARIETY"));
    assertTrue(basket.getThreeDeeCompatibleProducts().isEmpty());
  }

  @Test
  public void testCompatibleProducts() {
    Basket basket = new Basket(Arrays.asList("SPORTS", "NEWS", "MOVIES_1", "MOVIES_2"));
    assertEquals(4, basket.getThreeDeeCompatibleProducts().size());
  }

  @Test
  public void testEmptyProducts() {
    Basket basket = new Basket(Collections.emptyList());
    assertEquals(0, basket.getThreeDeeCompatibleProducts().size());
  }

  @Test
  public void testGenerateNotification() {
    Basket basket = new Basket(Arrays.asList("SPORTS", "NEWS"));
    String notification = basket.generateNotification(Arrays.asList("SPORTS_3D_ADD_ON", "NEWS_3D_ADD_ON"));
    assertEquals("All relevant addOns: [SPORTS_3D_ADD_ON, NEWS_3D_ADD_ON]", notification);
  }

  @Test
  public void testGenerateNotification_EmptyAddOn() {
    Basket basket = new Basket(Collections.emptyList());
    String notification = basket.generateNotification(Collections.emptyList());
    assertEquals("No 3D add-ons", notification);
  }
}
