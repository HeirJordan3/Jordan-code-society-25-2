package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import com.codedifferently.lesson17.bank.exceptions.MoneyOrderVoidedException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyOrderTest {

  private CheckingAccount sourceAccount;
  private SavingsAccount targetAccount;
  private MoneyOrder classUnderTest;

  @BeforeEach
  void setUp() {
    Customer customer = new Customer(UUID.randomUUID(), "John Doe");
    sourceAccount = new CheckingAccount("123456789", Set.of(customer), 200.0);
    targetAccount = new SavingsAccount("987654321", Set.of(customer), 300.0);
    classUnderTest = new MoneyOrder("MO001", 75.0, sourceAccount);
  }

  @Test
  void testConstructor_WithdrawsImmediately() {
    // Assert that funds were withdrawn from source account immediately upon creation
    assertThat(sourceAccount.getBalance()).isEqualTo(125.0); // 200 - 75
  }

  @Test
  void testConstructor_CantCreateMoneyOrderWithNegativeAmount() {
    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new MoneyOrder("MO002", -50.0, sourceAccount))
        .withMessage("Money order amount must be positive");
  }

  @Test
  void testConstructor_CantCreateMoneyOrderWithInsufficientFunds() {
    // Arrange - sourceAccount has 125.0 remaining after setUp

    // Act & Assert
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> new MoneyOrder("MO003", 200.0, sourceAccount))
        .withMessage("Account does not have enough funds for withdrawal");
  }

  @Test
  void testDepositFunds() {
    // Act
    classUnderTest.depositFunds(targetAccount);

    // Assert
    assertThat(targetAccount.getBalance()).isEqualTo(375.0); // 300 + 75
    assertThat(classUnderTest.isUsed()).isTrue();
  }

  @Test
  void testDepositFunds_MoneyOrderAlreadyUsed() {
    // Arrange
    classUnderTest.depositFunds(targetAccount);

    // Act & Assert
    assertThatExceptionOfType(MoneyOrderVoidedException.class)
        .isThrownBy(() -> classUnderTest.depositFunds(targetAccount))
        .withMessage("Money order has already been used");
  }

  @Test
  void testGetAmount() {
    assertThat(classUnderTest.getAmount()).isEqualTo(75.0);
  }

  @Test
  void testGetOrderNumber() {
    assertThat(classUnderTest.getOrderNumber()).isEqualTo("MO001");
  }

  @Test
  void testIsUsed_InitiallyFalse() {
    assertThat(classUnderTest.isUsed()).isFalse();
  }

  @Test
  void testIsUsed_TrueAfterDeposit() {
    classUnderTest.depositFunds(targetAccount);
    assertThat(classUnderTest.isUsed()).isTrue();
  }

  @Test
  void testHashCode() {
    // Arrange
    MoneyOrder otherMoneyOrder = new MoneyOrder("MO001", 100.0, sourceAccount);

    // Assert
    assertThat(classUnderTest.hashCode()).isEqualTo(otherMoneyOrder.hashCode());
  }

  @Test
  void testEquals() {
    // Arrange
    MoneyOrder sameOrderNumber = new MoneyOrder("MO001", 100.0, sourceAccount);
    MoneyOrder differentOrderNumber = new MoneyOrder("MO004", 75.0, sourceAccount);

    // Assert
    assertThat(classUnderTest.equals(sameOrderNumber)).isTrue();
    assertThat(classUnderTest.equals(differentOrderNumber)).isFalse();
  }

  @Test
  void testToString() {
    // Assert
    assertThat(classUnderTest.toString())
        .isEqualTo("MoneyOrder{orderNumber='MO001', amount=75.0, isUsed=false}");
  }

  @Test
  void testComparisonWithCheck() {
    // Arrange - Create another checking account for check deposit
    Customer customer = new Customer(UUID.randomUUID(), "Jane Doe");
    CheckingAccount checkingTarget = new CheckingAccount("555555555", Set.of(customer), 100.0);

    // Create a check for comparison
    Check check = new Check("CHK001", 75.0, sourceAccount);

    // Reset source account balance for fair comparison
    sourceAccount.deposit(75.0); // Restore what MoneyOrder took
    double balanceBeforeCheck = sourceAccount.getBalance();

    // Act - Deposit check into checking account
    check.depositFunds(checkingTarget);

    // Assert - Check behavior: withdrawal happens during deposit
    assertThat(sourceAccount.getBalance()).isEqualTo(balanceBeforeCheck - 75.0);
    assertThat(checkingTarget.getBalance()).isEqualTo(175.0); // 100 + 75

    // MoneyOrder behavior comparison: withdrawal happened at creation
    // The key difference is WHEN the withdrawal occurs:
    // - Check: withdraws during depositFunds()
    // - MoneyOrder: withdraws during constructor
  }
}
