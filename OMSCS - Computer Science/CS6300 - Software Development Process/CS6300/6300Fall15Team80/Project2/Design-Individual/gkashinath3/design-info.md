The design consists of 3 main classes that is used to compute the final bill for a customer and the sale process starts with the customer presenting his customer card:

###1) Customer Manager

If the customer doesn't have a customer card or wants to edit his customer information this class is invoked. This class takes care of adding a customer to the system, generating the unique ID and QR card. Customers information can also be edited using the sub-classes.

###2) Purchase Tracker

This class keeps track of purchases made by the customer. Based on the customer's transaction history the discounts and rewards are computed by the sub-classes.

###3) Credit Card Manager

This class takes care of the payment processing through a credit card at the time of purchase.
