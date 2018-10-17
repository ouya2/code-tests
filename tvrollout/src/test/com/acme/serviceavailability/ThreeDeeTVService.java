package test.com.acme.serviceavailability;

import com.acme.serviceavailability.TechnicalFailureException;

public interface ThreeDeeTVService {
  String getServiceAvailability(String postCode) throws TechnicalFailureException;
}
