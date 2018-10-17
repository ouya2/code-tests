package com.company.onlinestore;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public class Basket {

  public final static List<String> COMPATIBLE_PRODUCTS =
      Arrays.asList("SPORTS", "NEWS", "MOVIES_1", "MOVIES_2");

  private List<String> products;

  public Basket(List<String> products) {
    this.products = products;
  }

  //implement basket contents
  public List<String> getThreeDeeCompatibleProducts() {
    return products.stream()
                   .filter(e -> COMPATIBLE_PRODUCTS.contains(e))
                   .collect(Collectors.toList());
  }


  //implement notifications
  public String generateNotification(List<String> addOns) {
    return CollectionUtils.isEmpty(addOns) ? "No 3D add-ons" : "All relevant addOns: " + addOns;
  }
}
