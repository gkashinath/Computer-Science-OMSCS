package edu.gatech.seclass.scm.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.seclass.scm.model.exceptions.*;
import edu.gatech.seclass.scm.utils.DataLister;
import edu.gatech.seclass.services.EmailService;

/*
*
* import edu.gatech.seclass.scm.utils;*
 * Created by HARESH on 10/12/2015.
 */
public class Customer {

    DBAdapter myDb;

    public Customer(String name, String address, String emailAddress) {
        transactions = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.emailAddress = emailAddress;
        goldStatus = false;
        credit = 0;
        creditExpiryDate = new Date();
        lastPurchaseCalendarDate = new Date();
        discountTotalSinceJan = 0;
    }

    private Customer() { }

    public void setMyDb(DBAdapter myDb) {
        this.myDb = myDb;
    }

    private List<String> transactions;
    private String ID;
    private String name;
    private String address;
    private String emailAddress;
    private boolean goldStatus;
    private double credit;
    private Date creditExpiryDate;
    private Date lastPurchaseCalendarDate;
    private double discountTotalSinceJan;

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean getGoldStatus() {
        return goldStatus;
    }

    public void setGoldStatus(boolean goldStatus) {
        this.goldStatus = goldStatus;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getCreditExpiryDate() {
        return creditExpiryDate;
    }

    public void setCreditExpiryDate(Date creditExpiryDate) {
        this.creditExpiryDate = creditExpiryDate;
    }

    public Date getLastPurchaseCalendarDate() {
        return lastPurchaseCalendarDate;
    }

    public void setLastPurchaseCalendarDate(Date lastPurchaseCalendarDate) {
        this.lastPurchaseCalendarDate = lastPurchaseCalendarDate;
    }

    public double getDiscountTotalSinceJan() {
        return discountTotalSinceJan;
    }

    public void setDiscountTotalSinceJan(double discountTotalSinceJan) {
        this.discountTotalSinceJan = discountTotalSinceJan;
    }

    void record() throws CouldNotRecordCustomer {

        try {
            ID = myDb.addCustomer(this);
        } catch(DBWriteError e) {
            throw new CouldNotRecordCustomer();
       }
    }

    void pushTransactions(DataLister<Transaction> lister) throws CouldNotListTransactions {
            for(String x : transactions) {
            try {
                Transaction t = myDb.getTransaction(x);
                if(t == null)
                    throw new CouldNotListTransactions("Transacton ID" + x + " in customer" +
                                                        getID() + " doesn't exist");
                lister.push(t);
            } catch (DBReadError e) {
                throw new CouldNotListTransactions("Error reading transaction from database");
            }
        }
    }

    Transaction addPurchase(double subTotal, DataLister<Customer> onSuccess) throws CouldNotAddPurchase, CouldNotSendEmail,
            CouldNotReadCreditCard, CouldNotProcessCreditCard {
        List<String> discountCodes = new ArrayList<>();

        RewardManager rewardManager = new RewardManager(myDb);

        transactions = new ArrayList<>(transactions);

        // Compute the subtotal with discounts added
        double discountAddedSubtotal = subTotal;

       // If customer has gold status, add the gold discount to the list
        if(goldStatus) {
            discountCodes.add(RewardManager.GOLD_CODE);
            discountAddedSubtotal *= (1 - rewardManager.getGoldDiscountPercentage()/100.0);
        }

        // Create a new transaction
        Transaction transaction = new Transaction();

        // Set the transaction subtotal
        transaction.setSubTotal(subTotal);

        transaction.setDiscountTotal(subTotal - discountAddedSubtotal);

        // Check if we should apply any credit to the customer after the transaction
        boolean applyCredit = (discountAddedSubtotal >= rewardManager.getCreditThreshold());

        // If we are applying credit, add it to the discount list
        if(applyCredit)
            discountCodes.add(RewardManager.CREDIT_CODE);

        // Check if we should apply the gold status after thr transaction
        boolean applyGoldStatus = shouldApplyGold(discountAddedSubtotal);

        // Get today's date
        Calendar calendarToday = new GregorianCalendar();
        Date today = calendarToday.getTime();

        // Compute and set the credit to the transaction
        double creditApplied = 0;

        if(today.before(creditExpiryDate))
            creditApplied = ((discountAddedSubtotal - credit) >= 0 ? credit : discountAddedSubtotal);

        transaction.setCredit(creditApplied);

        // Add the discount list to the transaction
        transaction.setDiscounts(discountCodes);

        // Set the database adaptor
        transaction.setMyDb(myDb);

        // Apply the transaction
        String transactionID  = transaction.apply(this);

        // Back up the current customer details
        Customer backup = backup();

        // Update the customer details
        Calendar expDate = new GregorianCalendar();
        expDate.setTime(today);
        expDate.add(Calendar.YEAR, 1);
        creditExpiryDate = expDate.getTime();

        credit -= creditApplied;
        if(applyCredit == true)
            credit += rewardManager.getCreditPolicyAmount();

        Calendar lastPurchaseCalendar = new GregorianCalendar();
        lastPurchaseCalendar.setTime(lastPurchaseCalendarDate);

        if(calendarToday.get(Calendar.YEAR) > lastPurchaseCalendar.get(Calendar.YEAR))
            discountTotalSinceJan = discountAddedSubtotal;
        else
            discountTotalSinceJan += discountAddedSubtotal;

        goldStatus = (goldStatus) ? true : applyGoldStatus;

        lastPurchaseCalendarDate = today;

        transactions.add(transactionID);

        // Try to update the customer table. If not, undue changes and delete the transaction.
        try {
            myDb.updateCustomer(this);
        } catch (DBWriteError e) {
            restore(backup);
            transactions.remove(transactionID);
            myDb.deleteTransaction(transactionID);
            throw e;
        }

		// Try to send notification emails
        if(applyCredit)
            EmailService.sendMail(emailAddress, rewardManager.getCreditEmailSubject(),
                                  rewardManager.getCreditEmailMessage());

        if(applyGoldStatus)
            EmailService.sendMail(emailAddress, rewardManager.getGoldEmailSubject(),
                                  rewardManager.getGoldEmailMessage());;

        onSuccess.push(this);

        return transaction;
    }

    Transaction computePurchase(double subTotal)
    {
        List<String> discountCodes = new ArrayList<>();

        RewardManager rewardManager = new RewardManager(myDb);

        // Compute the subtotal with discounts added
        double discountAddedSubtotal = subTotal;

        // If customer has gold status, add the gold discount to the list
        if(goldStatus) {
            discountCodes.add(RewardManager.GOLD_CODE);
            discountAddedSubtotal *= (1 - rewardManager.getGoldDiscountPercentage()/100.0);
        }

        // Create a new transaction
        Transaction transaction = new Transaction();

        // Set the transaction subtotal
        transaction.setSubTotal(subTotal);

        transaction.setDiscountTotal(subTotal - discountAddedSubtotal);

        // Check if we should apply any credit to the customer after the transaction
        boolean applyCredit = (discountAddedSubtotal >= rewardManager.getCreditThreshold());

        // If we are applying credit, add it to the discount list
        if(applyCredit)
            discountCodes.add(RewardManager.CREDIT_CODE);

        // Get today's date
        Calendar calendarToday = new GregorianCalendar();
        Date today = calendarToday.getTime();

        // Compute and set the credit to the transaction
        double creditApplied = 0;

        if(today.before(creditExpiryDate))
            creditApplied = ((discountAddedSubtotal - credit) >= 0 ? credit : discountAddedSubtotal);

        transaction.setCredit(creditApplied);

        // Add the discount list to the transaction
        transaction.setDiscounts(discountCodes);

        transaction.setDate(new Date());

        transaction.setTotal((subTotal - transaction.getDiscountTotal()) - creditApplied);

        return transaction;
    }

    private boolean shouldApplyGold(double discountAddedValue) {
        Calendar calendarToday = new GregorianCalendar();
        Calendar lastPurchaseCalendar = new GregorianCalendar();
        lastPurchaseCalendar.setTime(lastPurchaseCalendarDate);
        RewardManager rewardManager = new RewardManager(myDb);

        if((calendarToday.get(Calendar.YEAR) == lastPurchaseCalendar.get(Calendar.YEAR)) &&
                ((discountAddedValue + discountTotalSinceJan) >= rewardManager.getGoldDiscountThreshold()) &&
                (goldStatus == false)) {
            return true;
        }

        return false;
    }

    private Customer backup() {
        Customer backup = new Customer();

        backup.ID = ID;
        backup.name = name;
        backup.address = address;
        backup.emailAddress = emailAddress;
        backup.goldStatus = goldStatus;
        backup.credit = credit;
        backup.creditExpiryDate = creditExpiryDate;
        backup.lastPurchaseCalendarDate = lastPurchaseCalendarDate;
        backup.discountTotalSinceJan = discountTotalSinceJan;

        return backup;
    }

    private void restore(Customer backup) {
        ID = backup.ID;
        name = backup.name;
        address = backup.address;
        emailAddress = backup.emailAddress;
        goldStatus = backup.goldStatus;
        credit = backup.credit;
        creditExpiryDate = backup.creditExpiryDate;
        lastPurchaseCalendarDate = backup.lastPurchaseCalendarDate;
        discountTotalSinceJan = backup.discountTotalSinceJan;
    }
}
