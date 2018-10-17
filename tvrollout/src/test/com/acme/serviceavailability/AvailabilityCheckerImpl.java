package test.com.acme.serviceavailability;

import com.acme.serviceavailability.AvailabilityChecker;
import com.acme.serviceavailability.TechnicalFailureException;

public class AvailabilityCheckerImpl implements AvailabilityChecker {

  private ThreeDeeTVService threeDeeTVService;

  public AvailabilityCheckerImpl(ThreeDeeTVService threeDeeTVService) {
    this.threeDeeTVService = threeDeeTVService;
  }

  public String isPostCodeIn3DTVServiceArea(String postCode) throws TechnicalFailureException {
    return threeDeeTVService.getServiceAvailability(postCode);
  }
}
