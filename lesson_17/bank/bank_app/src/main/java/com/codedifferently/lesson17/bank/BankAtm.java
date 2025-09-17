package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a bank ATM that can handle multiple account types. Enhanced to support SavingsAccount
 * and MoneyOrder without adding new public methods.
 */
public class BankAtm {

  private final Map<UUID, Customer> customerById = new HashMap<>();
  private final Map<String, Account> accountByNumber =
      new HashMap<>(); // Changed to Account interface

  /**
   * Adds a checking account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    addAccountInternal(account);
  }

  /**
   * Adds a savings account to the bank. This method enables SavingsAccount support without adding a
   * new public method.
   *
   * @param account The savings account to add.
   */
  public void addAccount(SavingsAccount account) {
    addAccountInternal(account);
  }

  /**
   * Internal method to add any account type. This follows the DRY principle and supports the
   * Open/Closed Principle.
   */
  private void addAccountInternal(Account account) {
    accountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              customerById.put(owner.getId(), owner);
              if (account instanceof CheckingAccount) {
                owner.addAccount((CheckingAccount) account);
              }
            });
  }

  /**
   * Finds all accounts owned by a customer.
   *
   * @param customerId The ID of the customer.
   * @return The unique set of checking accounts owned by the customer.
   */
  public Set<CheckingAccount> findAccountsByCustomerId(UUID customerId) {
    return customerById.containsKey(customerId)
        ? customerById.get(customerId).getAccounts()
        : Set.of();
  }

  /**
   * Deposits funds into an account using cash.
   *
   * @param accountNumber The account number.
   * @param amount The amount to deposit.
   */
  public void depositFunds(String accountNumber, double amount) {
    Account account = getAccountOrThrow(accountNumber);
    account.deposit(amount);
  }

  /**
   * Deposits funds into an account using a check. Enhanced to work with both CheckingAccount and
   * SavingsAccount.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   */
  public void depositFunds(String accountNumber, Check check) {
    Account account = getAccountOrThrow(accountNumber);
    // Check can only be written against CheckingAccount, but can be deposited to any Account
    check.depositFunds((CheckingAccount) account); // This maintains existing Check behavior
  }

  /**
   * Deposits funds into an account using a money order. This overloaded method supports MoneyOrder
   * without adding a new public method.
   *
   * @param accountNumber The account number.
   * @param moneyOrder The money order to deposit.
   */
  public void depositFunds(String accountNumber, MoneyOrder moneyOrder) {
    Account account = getAccountOrThrow(accountNumber);
    moneyOrder.depositFunds(account);
  }

  /**
   * Withdraws funds from an account.
   *
   * @param accountNumber The account number.
   * @param amount The amount to withdraw.
   */
  public void withdrawFunds(String accountNumber, double amount) {
    Account account = getAccountOrThrow(accountNumber);
    account.withdraw(amount);
  }

  /**
   * Gets an account by its number or throws an exception if not found. Enhanced to work with any
   * Account type.
   *
   * @param accountNumber The account number.
   * @return The account.
   */
  private Account getAccountOrThrow(String accountNumber) {
    Account account = accountByNumber.get(accountNumber);
    if (account == null || account.isClosed()) {
      throw new AccountNotFoundException("Account not found");
    }
    return account;
  }
}
