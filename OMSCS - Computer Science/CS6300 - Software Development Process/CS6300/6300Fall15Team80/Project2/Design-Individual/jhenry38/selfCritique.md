

For the Customer Class, we decided that the "sumOfPurchases" attribute is not necessary.  The sum of the purchases is not explicilty listed in the requirements only the specific transaction price is needed.

In our team discussion, there was a question about how the QR card is obtained.  
how do you get the QR card

Also, since the credit card information is not stored locally and only used to process transactions, the Credit Card class is not needed and there shoud be no credit card attribute in the Customer class.  Therefore the Credit Card class was removed and the creditDate attribute was removed from the Customer class.

The entire Credit Card Class is replaced with a utility function called "processWithPaymentService" which handles all of the data about the Credit Card being used and the interaction with the external transaction processing service.

The method "listRewards" is not needed since it is not explicilty listed in the requirements and the details about the rewards are associated with the Transaction class.

viewCustomerData is not needed since the capability to specifically list the data about a customer is not listed as a requirement.

The method "sumOfYearPurchases" is not needed in the Customer Class.  This data is only needed to determine "Gold Status" and should be handled in the Rewards Calss or sub Classes

The "Items" class is not requried since only the final transaction amount is needed for proper functionality.

The attribute "creditValue" shoudl not be stored at the Gold Class level and should be calculated in the Reward Class.

The attribute "creditValue" in the Credit Class shoudl have a default value of $5.
