import java.util.Scanner;

// ATM Machine Class
class ATM {
  private double balance;
  private int pin;
  private boolean isLocked;

  // Constructor to initialize ATM with a PIN and initial balance
  public ATM(int pin, double initialBalance) {
    this.pin = pin;
    this.balance = initialBalance;
    this.isLocked = false;
  }

  // Method to authenticate user by checking PIN
  public boolean authenticate(int enteredPin) {
    return this.pin == enteredPin;
  }

  // Method to lock the account after 3 failed attempts
  public void lockAccount() {
    this.isLocked = true;
  }

  // Method to check if the account is locked
  public boolean isLocked() {
    return isLocked;
  }

  // Method to change the PIN
  public void changePin(int newPin) {
    this.pin = newPin;
    System.out.println("PIN changed successfully. Please log in again with the new PIN.");
  }

  // Method to get the current PIN
  public int getPin() {
    return pin;
  }

  // Method to check and display account balance
  public void checkBalance() {
    System.out.println("Your current balance is: Rs " + balance);
  }

  // Method to deposit money into the account
  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      System.out.println("Rs " + amount + " deposited successfully.");
      checkBalance(); // Display updated balance
    } else {
      System.out.println("Invalid deposit amount.");
    }
  }

  // Method to withdraw money from the account
  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.println("Rs " + amount + " withdrawn successfully.");
      checkBalance(); // Display updated balance
    } else {
      System.out.println("Insufficient balance or invalid amount.");
    }
  }
}

// Main Class to handle ATM operations
public class ATMMachine {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ATM userATM = new ATM(1234, 1000.00); // Default PIN: 1234, Initial Balance: Rs 1000

    while (true) {
      int attempts = 0;
      while (attempts < 3) {
        System.out.print("\nEnter your PIN: ");
        int enteredPin = sc.nextInt();

        if (userATM.authenticate(enteredPin)) {
          break; // Exit loop if authentication is successful
        } else {
          attempts++;
          System.out.println("Invalid PIN! Attempts left: " + (3 - attempts));
        }
      }

      // Lock the account if 3 wrong attempts are made
      if (attempts == 3) {
        userATM.lockAccount();
        System.out.println("Account locked due to multiple incorrect attempts.");
        return; // Exit the program
      }

      while (true) {
        // Display ATM menu
        System.out.println("\n======= ATM Machine =======");
        System.out.println("1. Check A/C Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Change PIN");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int ch = sc.nextInt();

        // Handle user's choicekkkkk
        switch (ch) {
          case 1:
            userATM.checkBalance(); // Check account balance
            break;
          case 2:
            System.out.print("Enter amount to deposit: ");
            double depositAmount = sc.nextDouble();
            userATM.deposit(depositAmount); // Deposit money
            break;
          case 3:
            System.out.print("Enter amount to withdraw: ");
            double withdrawAmount = sc.nextDouble();
            userATM.withdraw(withdrawAmount); // Withdraw money
            break;
          case 4:
            System.out.print("Enter new PIN: ");
            int newPin = sc.nextInt();
            userATM.changePin(newPin); // Change the PIN
            break;
          case 5:
            System.out.println("Thank you for using our ATM. Have a great day!");
            sc.close();
            return; // Exit the program
          default:
            System.out.println("Invalid choice! Please try again.");
        }

        // If PIN was changed, require re-authentication before further transactions
        if (ch == 4) {
          break;
        }
      }
      sc.close();
    }
  }
}