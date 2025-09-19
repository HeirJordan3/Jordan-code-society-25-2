package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Represents a business checking account that requires at least one business owner. */
public class BusinessCheckingAccount extends CheckingAccount {

    public BusinessCheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
        super(accountNumber, owners, initialBalance);
        validateBusinessOwners(owners);
    }

    private void validateBusinessOwners(Set<Customer> owners) {
        boolean hasBusinessOwner = owners.stream()
            .anyMatch(customer -> customer.getCustomerType() == CustomerType.BUSINESS);
        
        if (!hasBusinessOwner) {
            throw new IllegalArgumentException(
                "Business checking account must have at least one business owner");
        }
    }
}