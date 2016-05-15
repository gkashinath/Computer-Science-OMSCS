# Smoothie Cart User Manual #

## 1 - Intended User of App ##

This Android app is intended to be used by the manager of a mobile smoothie cart.  This app has the ability to store customer information, transaction history, process credit cards, and compute rewards based on transaction properties.

When the app is opened, the following screen is shown.  Hit the "Enter" button to get started.

![alt text][StartPage]

## 2 - Overview of App ##

This app is a simple user interfeace that allows the user to easily add customer information, process a transaction, compute rewards, and store historical information about customers and transactions.  This app fully handles the customer and transaction side of running a smoothie cart.  Also, the reward system values can be modified by the user.  For example, the default credit value for a user who spends $50 or more in a single transaction is $5.  The user has the ability to modify both the threshold and credit value.

![alt text][MainMenu]

## 3 - Customer Data ##

The app has the ability to both add a new customer and update a  handle customer data in the following way:
-Add Customer
-Edit Customer Data
-Print Transactions associated with a customer

To access the Customer page, touch the "Customer" button in the Main Menu.

![alt text][ManageCustomer]

### 3a - Add a Customer ###

Touching the "Add" button from the "Manage Customer" screen will bring you to the "Add Customer Info" page.  To add a customer, fill in the name, address, and email address.  Once these values are filled in, hit the "Submit" button.  This will generate a unique ID, populate the Customer ID field and add the customer to the database.  If the customer is added successfully, a notification will alert the user, tha the QR card will be automatically printed.

![alt text][AddCustomerInfo]

### 3b - Edit a Customer ###

Once the customer data has been added to the database, the customer data can be modified by selecting the "Edit" button from teh "Manage Customer" page.  To bring up the customer data that needs to be changed, scan the QR code on the customers card and the data associated with that customer will be displayed on the screen.  Now the user can change the Name, Address, and Email Address.  To commit the changes, hit the "Submit" button on the bottom of the screen

![alt text][EditCustomerInfo]

### 3c - View Customer Transactions ###

To see a list of a customer's transactions, hit the "Print Transaction" button from the "ManageCustomer" page.  Then, scan the QR card and the customer's name and ID will be displayed on the screen.  To list the customer's transactions, hit the "Print" button.  This will populate the text field with a list of all transactions associated with the customer.  To scroll through the list, click and drag within the list.

![alt text][PrintTransactions]

## 4 - Rewards ##

The default rewards in the app are as follows.  When a user spends $50 in a single transaction, they will get a $5 credit for future purchases.  This credit must be used on the next transaction.  If the transaction is less than the credit amount, only the amount of the transaction will be deducted from the credit amount and the remaining credit will be used on the next transaction.

Additionally, the Gold Status reward is achieved when the user spends $500 or more in the current calendar year.  Once Gold Status is achieved, it is permanent and the customer will get 5% off all future transactions.

The user has the ability to set the rewards thresholds and amounts via the app.  The user also has the ability to see the current rewards associated with a customer.

![alt text][RewardsManager]

### 4a - Editing Rewards ###

to see the current values for the rewards, hit the "SET STORE POLICY" button on the "Rewards Manager" page.  This will bring you to the "Set Rewards" page.  The fields will be populated with the current values used by the app.  To change these values, enter the new value in the associated text field and hit "Submit".  Once complete, all future transactions will use these values for determining rewards.

![alt text][SetRewards]

### 4b - Viewing Customer Rewards ###

To view the current rewards associated with a particular customer, hit the "GET CUSTOMER REWARDS" button from the "Rewards Manager".  This will bring you to the "Get Rewards" page where you will be able to scan a customer's QR card.  Once the QR card is scanned, the current value of the customers various rewards will be populated in the text fields.

![alt text][GetRewards]

## 5 - Transactions ##

To process a Transaction, hit the "Transaction" button from the main menu.  This will bring you to the "Transaction Summary" page where a customer QR card can be scanned, transaction data can be entered, reward data applied, and a credit card can be processed.

### 5 - Enter Transaction ###

To enter a transaction, the user must first scan a QR card to obtain the customer information.  If the customer is new, please refer to the "Add a Customer" section of the manual.  Once the QR card is scanned successfully, the Name and Customer ID fields will be populated.  Enter the total price for the current transaction and hit "Submit".  This will apply any potential discounts and calculate a final price.  Once the final price is calculated, you can process the Credit Card by hitting the "APPLY TRANSACTION" button.

When the transaction is applied, the customer's credit card will be processed based on the final price calculated and displayed on the screen.  The user will be notified if the transaction is successfull or not.

If the customer has earned a credit or earned Gold Status, they will be automatically emailed when the transaction is successfully processed using the email address stored in the customer database.

![alt text][TransactionSummary]



[StartPage]: ScreenSnapshots/StartPage.PNG "StartPage"
[MainMenu]: ScreenSnapshots/MainMenu.PNG "MainMenu"
[AddCustomerInfo]: ScreenSnapshots/AddCustomerInfo.PNG "AddCustomerInfo"
[EditCustomerInfo]: ScreenSnapshots/EditCustomerInfo.PNG "EditCustomerInfo"
[ManageCustomer]: ScreenSnapshots/ManageCustomer.PNG "ManageCustomer"
[PrintTransactions]: ScreenSnapshots/PrintTransactions.PNG "PrintTransactions"
[RewardsManager]: ScreenSnapshots/RewardsManager.PNG "RewardsManager"
[SetRewards]: ScreenSnapshots/SetRewards.PNG "SetRewards"
[GetRewards]: ScreenSnapshots/GetRewards.PNG "GetRewards"
[TransactionSummary]: ScreenSnapshots/TransactionSummary.PNG "TransactionSummary"


[Main_Menu]: ScreenSnapshots/Main_Menu.PNG "MainMenu"
[Get_Rewards]: ScreenSnapshots/Get_Rewards.PNG "GetRewards"
[Print_Transactions]: ScreenSnapshots/Print_Transactions.PNG "PrintTransactions"
[Add_Edit_Customer_Info]: ScreenSnapshots/AddEdit_Customer_Info.PNG "Add/Edit Customer Info"
[Process_Transaction]: ScreenSnapshots/Process_Transaction.PNG "Process Transactions"
[Rewards_Manager]: ScreenSnapshots/Rewards_manager.PNG "Rewards Manager"
[Set_Rewards]: ScreenSnapshots/Set_Rewards.PNG "Set Rewards"
[Transaction_Summary]: ScreenSnapshots/Transaction_Summary.PNG "Transaction Summary"
[Transaction]: ScreenSnapshots/Transaction.PNG "Transaction"
