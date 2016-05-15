#Vision Document

**Author**: Haresh Bhagchandani

## 1 Introduction

Smoothie Cart Manager is a payment and reward manager for a small business. It is designed to run on Android phones and to help automate this management.

## 2 Business Needs/Requirements

Brad and Janet own and run an organic smoothie selling cart in a park in Atlanta on weekends. Although it seems like a fairly small business, it is picking up momentum, so Brad and Janet want to automate a few aspects of their business. They want to automate the purchasing by credit card of customers. They also want to automate the rewarding of their most loyal customers. They came to us with their set of requirements and requested that the system run on the Android platform. Undoubtedly this will help them to use their devices as portable sales machines and to help them automate business transactions in the park. It will also help them to keep up with the growing demand for their smoothies.

## 3 Product / Solution Overview

Smoothie Cart Manager:

* integrates with an external payment service to automatically process credit card payments
* interacts with a QR scanner to allow for customers to swipe a customer card and make a purchase
* interacts with a credit card scanner to make transactions quick and painless
* keeps track of customer credits earned and applies it automatically to future purchases
* supports the management of discounts, such as gold customer status, and apply it to purchases
* keeps track of a customers transactions
* keeps track of discounts applied to each transaction
* automatically notifies customers when a reward is earned due to a purchase through e-mail

## 4 Scope and Limitations

This system will not support cash payments. All purchases will be assumed to be via a credit card.

The system will not store any credit card details of a customer. At each transaction the card will be swiped and the external payment processing service will transfer the credit from the customer to the business. 
