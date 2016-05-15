Listed below are the global requirements for the Smoothie App;

1. RG1 - The smoothie cart manager uses the system to add customers.
2. RG2 - The smoothie cart manager uses the system to edit customer information.
3. RG3 - The smoothie cart manager uses the system to process purchases.
4. RG4 - The smoothie cart manager uses the system to keep track of purchases and rewards.

Listed below are the detailed/local requirements for the Smoothie App;

1. RL1 - A customer in the system is characterized by name, billing address, email address, 32-digit unique hexadecimal ID.
2. RL2 - When a customer is added to the system, he/she is provided with a customer QR card which has the encoded 32-digit hexadecimal customer ID.
3. RL3 - All payments must be performed using a credit card using the credit card scanner and transaction processing system.
4. RL4 - Every time a customer spends $50 or more in a single purchase, he/she gets a $5 credit towards his/her next purchase as a reward.
  1. RL4.1 - The credit expires after one year.
  2. RL4.2 - The credit is applied towards the next purchase; that is, a customer cannot choose to save the credit for a future purchase. 
  3. RL4.3 - To get the $5 credit, the customer must actually pay $50 or more; that is, the amount is computed after any existing discount is applied. For example, if a customer buys $52 worth of smoothies and has a $3 credit, he/she would spend $49 and would not get the $5 credit.
  4. RL4.4 - If the cost C of the purchase is less than the current credit for that customer, the credit is decreased by C. For example, if the current credit is $5, and the cost of the purchase is $3.5, the customer pays $0, and the remaining credit is $1.5.
  5. RL4.5 - The system should send an email to a customer when he/she gets a credit.
5. RL5 - Customers who spend $500 or more in a calendar year achieve "gold" status, which entitles them to a 5% discount for life and on every purchase. 
  1. RL5.1 - The 5% discount is applied before any other discount.
  2. RL5.2 - The system should send an email to a customer when he/she achieves "gold" status.
6. RL6 - At any particular  point in time, the smoothie cart manager should be able to display, for any customer in the system, a list of their transactions. For each transaction, the smoothie cart manager should be able to see (1) date, (2) amount of purchase before discounts, and (3) whether discounts were applied (and which ones).
7. RL7 - Emails are sent out when gold status is achieved and also when credits are applied.

There were 4 main use cases that were identified for the Smoothie app;

1. UC1 - Add Customer
2. UC2 - Edit Policies
3. UC3 - Edit Customer
3. UC4 - View Customer Transactions
4. UC5 - Add Purchase

Below are the core APIs used to implement the Smoothie App.

1. CD1 CustomerManager.addCustomer
2. CD2 CustomerManager.getCustomerByQRCard
3. CD3 CustomerManager.editCustomerDetails
4. CD4 CustomerManager.getCustomerByID
5. CD5 CustomerManager.addPurchase
6. CD6 CustomerManager.computTransactions
7. CD7 RewardManager.getCreditPolicyAmount
8. CD8 RewardManager.getCreditThreshold
9. CD9 RewardManager.getGoldDiscountPercentage
10. CD10 RewardManager.getGoldDiscountThreshold
11. CD11 RewardManager.setCreditPolicyAmount
12. CD12 RewardManager.setCreditThreshold
13. CD13 RewardManager.setGoldDiscountPercentage
14. CD14 RewardManager.setGoldDiscountThreshold
15. CD15 DBAdapter.getTransaction

The test cases are listed in the test plan document and the tags for the test cases will be cross-referenced in the table below that summarizes the traceability of the requirements and use cases with corresponding tests.

| Test Case | Use Case | Requirements | APIs used | Test Performed |
|:---: |:---: | :---: | :---: | :---: |
| T1.1.1 | UC1, UC3, UC4 | RG1, RG2, RG4, RL1 | N/A | Y |
| T1.1.2 | N/A | N/A | N/A | Y |
| T1.2.1 | UC1 | RG1 | N/A | Y |
| T1.2.2 | UC1 | RG1, RL1 | N/A | Y |
| T1.2.3 | UC1 | RG1, RL2 | N/A | Y |
| T1.2.4 | UC1 | RG1 | CD1 | Y |
| T1.2.5 | UC1 | RG1 | N/A | Y |
| T1.2.6 | UC1 | RG1 | N/A | Y |
| T1.2.7 | UC1 | RG1 | N/A | Y |
| T1.3.1 | UC3 | RG2 | N/A |Y |
| T1.3.2 | UC3 | RG2 | CD2 | Y |
| T1.3.3 | UC3 | RG2 | CD2 | Y |
| T1.3.4 | UC3 | RG2 | CD3 |Y |
| T1.3.5 | UC3 | RG2 | N/A | Y |
| T1.3.6 | UC4 | RG4 | CD2 | Y |
| T1.3.7 | UC4 | RG4 | CD4, CD15 | Y |
| T2.1.1 | UC2 | RL4, RL5 | N/A | Y |
| T2.1.2 | N/A | N/A | N/A | Y |
| T2.2.1 | UC2 | RL4, RL5 | N/A | Y |
| T2.2.2 | UC2 | RL4, RL5 | CD7, CD8, CD9, CD10 | Y |
| T2.2.3 | UC2 | RL4, RL5 | N/A | Y |
| T2.2.4 | UC2 | RL4, RL5 | CD11, CD12, CD13, CD14 | Y |
| T2.3.1 | UC4 | RG4, RL6 | N/A | Y |
| T2.3.2 | UC4 | RG4, RL6 | CD2 | Y |
| T3.1.1 | UC5 | RG3, RL3, RL4, RL5 | N/A | Y |
| T3.1.2 | N/A | RG1, RG2 | N/A | Y |
| T3.1.3 | UC5 | RG3 | CD2 | Y |
| T3.1.4 | UC5 | RG3 | N/A | Y |
| T3.1.5 | UC5 | RG3 | CD6 | Y |
| T3.1.6 | UC5 | RG3 | CD6 |  Y |
| T3.1.7 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.8 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.9 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.10 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.11 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.12 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.13 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.14 | UC5 | RG3, RL3, RL4, RL5 | CD6 |  Y |
| T3.1.15 | UC5 | RG3, RL3, RL4, RL5 | CD5 | Y |
| T3.1.16 | UC5 | RG4 | N/A | Y |
| T3.1.17 | UC5 | RG4 | N/A | Y |
| T4.1.1 | N/A | RL7 | CD5 | Y |
| T4.1.2 | N/A | RL7 | CD5 | Y |

The N/A fields satisfy mostly app navigational features.
