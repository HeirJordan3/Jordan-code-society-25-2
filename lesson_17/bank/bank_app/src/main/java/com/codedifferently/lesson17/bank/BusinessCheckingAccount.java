package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Represents a business checking account that requires at least one business owner. */
public class BusinessCheckingAccount extends CheckingAccount {

  public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    validateBusinessOwners(owners);
  }

  private void validateBusinessOwners(Set<Customer> owners) {
    // Step 1: Check if we have any owners at all
    if (owners == null || owners.isEmpty()) {
      throw new IllegalArgumentException(
          "Business checking account must have at least one business owner");
    }

    boolean foundBusinessOwner = false;

    for (Customer customer : owners) {
      // Check if this customer is a business type
      if (customer.getCustomerType() == CustomerType.BUSINESS) {
        foundBusinessOwner = true;
        break; // We found one, no need to keep looking
      }
    }

    // Step 3: If we didn't find any business owners, throw an error
    if (!foundBusinessOwner) {
      throw new IllegalArgumentException(
          "Business checking account must have at least one business owner");
    }
  }
}
