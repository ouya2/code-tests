package com.company.onlinestore;

import com.acme.serviceavailability.AvailabilityChecker;
import com.acme.serviceavailability.TechnicalFailureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class ThreeDeeAddOnServiceImpl implements ThreeDeeAddOnService {

  private AvailabilityChecker checker;

  public ThreeDeeAddOnServiceImpl(AvailabilityChecker checker) {
    this.checker = checker;
  }

  public void checkFor3DAddOnProducts(Basket basket, String postCode) {
    String availabilityResult = null;
    try {
      availabilityResult = checker.isPostCodeIn3DTVServiceArea(postCode);
    }
    catch (TechnicalFailureException e) {
      // logging the exception here
      System.err.println("Postcode " + postCode + " is not in any 3D TV service area");
      return;
    }
    if (availabilityResult.equals("SERVICE_AVAILABLE")) {
      List<String> allCompatibleProducts = basket.getThreeDeeCompatibleProducts();
      List<String> addOns = findAddOns(allCompatibleProducts);
      System.out.println(basket.generateNotification(addOns));
    }
    else {
      System.out.println("No 3D add-ons");
    }
  }

  private List<String> findAddOns(List<String> allCompatibleProducts) {
    if (CollectionUtils.isEmpty(allCompatibleProducts)) {
      return Collections.emptyList();
    }

    List<String> addOns = new ArrayList<>();
    for(String product : allCompatibleProducts) {
      if (product.contains("SPORTS")) {
        addOns.add("SPORTS_3D_ADD_ON");
      }
      if (product.contains("NEWS")) {
        addOns.add("NEWS_3D_ADD_ON");
      }
      if (product.contains("MOVIES")) {
        addOns.add("MOVIES_3D_ADD_ON");
      }
    }
    return addOns;
  }
}
