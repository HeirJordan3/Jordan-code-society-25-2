package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.MoneyOrderVoidedException;

/**
 * Represents a money order that withdraws funds immediately upon creation. Unlike a check, the
 * funds are withdrawn from the source account when the MoneyOrder is created.
 */
public class MoneyOrder {

  private final String orderNumber;
  private final double amount;
  private boolean isUsed = false;

  /**
   * Creates a new money order and immediately withdraws funds from the source account.
   *
   * @param orderNumber The money order number.
   * @param amount The amount of the money order.
   * @param sourceAccount The account to withdraw funds from immediately.
   */
  public MoneyOrder(String orderNumber, double amount, Account sourceAccount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Money order amount must be positive");
    }
    this.orderNumber = orderNumber;
    this.amount = amount;

    // Withdraw funds immediately upon creation - this is the key difference from Check
    sourceAccount.withdraw(amount);
  }

  /**
   * Gets the used status of the money order.
   *
   * @return True if the money order has been used, and false otherwise.
   */
  public boolean isUsed() {
    return isUsed;
  }

  /**
   * Gets the amount of the money order.
   *
   * @return The amount of the money order.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the order number.
   *
   * @return The order number.
   */
  public String getOrderNumber() {
    return orderNumber;
  }

  /**
   * Deposits the money order into an account. Since funds were already withdrawn, this only
   * deposits to the target account.
   *
   * @param toAccount The account to deposit the money order into.
   */
  public void depositFunds(Account toAccount) {
    if (isUsed) {
      throw new MoneyOrderVoidedException("Money order has already been used");
    }
    toAccount.deposit(amount);
    isUsed = true;
  }

  @Override
  public int hashCode() {
    return orderNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof MoneyOrder other) {
      return orderNumber.equals(other.orderNumber);
    }
    return false;
  }

  @Override
  public String toString() {
    return "MoneyOrder{"
        + "orderNumber='"
        + orderNumber
        + '\''
        + ", amount="
        + amount
        + ", isUsed="
        + isUsed
        + '}';
  }
}
