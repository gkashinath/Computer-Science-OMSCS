# Use Case Model

## 1 Use Case Diagram

![alt text][useCaseDiagram]

## 2 Use Case Descriptions

### Add Customer (UC-1)

*Requirements*: Allow the manager to add a new customer to the system.

*Pre-conditions*: None.

*Post-conditions*: New customer has been added to database, New customer card has been printed.

*MAIN SUCCESS SCENARIO*

1. Manager inputs customer's name, billing address and email address.

2. System generates a unique 32 digit hexadecimal id for the customer.

3. System adds customer to database.

4. System prints out a customer card with the encoded id.

### Edit Policies (UC-2)

*Requirements*: Allow the manager to edit store policies for the system.

*Pre-conditions*: None

*Post-conditions*: Policies have been updated.

*MAIN SUCCESS SCENARIO*

1. Manager inputs the credit threshold, gold status threshold, credit amount and gold status percentage into the system.

3. System updates the store polices in database.


### Edit Customer (UC-3)

*Requirements*: Allow the manager to edit customer information stored in the system.

*Pre-conditions*: Customer already exists in the system,

*Post-conditions*: Customer details have been updated.

*MAIN SUCCESS SCENARIO*

1. Manager scans the customer's QR Card into the system.

2. Manager inputs the new name, billing address and email address details of the customer.

3. System updates the customer details in database.

### View Customer Transactions (UC-4)

*Requirements*: Allow the manager to view a customer's transactions.

*Pre-conditions*: Customer already exists in the system.

*Post-conditions*: Customer transactions have been listed.

*MAIN SUCCESS SCENARIO*

1. Manager inputs QR Card into the system.

2. System displays the date, subtotal, discounts applied, credit applied and the total of each transaction a customer has made.


### Add Purchase (UC-5)

*Requirements*: Allows the manager to record a new purchase by a customer.

*Pre-conditions*: Customer already exists in the system.

*Post-conditions*: New customer transaction has been added to the system, rewards earned have been added to the customer's profile

*MAIN SUCCESS SCENARIO*

1. Manager enters the subtotal of the purchase.

2. Manager scans the QR code of the customer's card.

3. If the customer has gold status, system adds that discount to the list of discounts applied.

4. System opens a new transaction and adds the subtotal to it.

5. System applies every discount to the transaction.

6. If the subtotal with discounts applied is greater or equal to the credit threshold, the system adds the policy credit amount to the customer's account, adds it to the list of discounts applied and update its expiry date and notifies him/her AFTER the transaction has been successfully closed.

7. If the subtotal with discounts applied since January is greater or equal to the gold status threshold, the system adds gold status to the customer's account and notifies him/her AFTER the transaction has been successfully closed.

8. If the customer's credit expiry date has not passed, the system applies as much credit as possible to the transaction.

9. System displays a transaction preview 

10. Manager signals system to continue with the transaction.

11. If the transaction total is not $0, the system displays it and waits for the manager to scan the customer's credit card.

12. The manager scans the customer's credit card.

13. The system passes to the external payment processor the cardholder's name, the card's account number, the card's expiry date and the card's security code.

14. The system successfully closes the transaction when the payment is approved.

15. The system adds the transaction to the database and notifies the manager.

*EXTENSIONS*

14-15a: The payment is not approved.

14-15a1. System closes the transaction as a failure and discards it.

14-15a2. System notifies the manager of failure.

11-15a: Transaction total is $0.

11-15a1. System successfully closes the transaction.

11-15a2. The system adds the transaction to the database and notifies the manager.

[useCaseDiagram]: UseCaseDiagram.png "Use Case Diagram"
