package model;

import java.util.Objects;

/**
 * The entity has the phone number and word mapping.
 */
public class PhoneNumberWordEntry {

  private String phoneNumber;
  private String word;

  public PhoneNumberWordEntry(String phoneNumber, String word) {
    this.phoneNumber = phoneNumber;
    this.word = word;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumberWordEntry that = (PhoneNumberWordEntry) o;
    return phoneNumber == that.phoneNumber &&
        Objects.equals(word, that.word);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumber, word);
  }
}
