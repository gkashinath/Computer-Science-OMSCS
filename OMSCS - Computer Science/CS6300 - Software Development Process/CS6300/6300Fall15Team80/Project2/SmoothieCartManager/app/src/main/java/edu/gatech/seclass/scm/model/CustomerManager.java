package edu.gatech.seclass.scm.model;

import android.database.sqlite.SQLiteDatabase;

import edu.gatech.seclass.scm.model.exceptions.*;
import edu.gatech.seclass.scm.utils.CreditCardPrinter;
import edu.gatech.seclass.scm.utils.DataLister;
import edu.gatech.seclass.services.QRCodeService;

/**
 *
 */
public class CustomerManager
{
    //static DBAdapter myDb;
    private SQLiteDatabase database;
    private DBAdapter dbAdapter;

    public CustomerManager(DBAdapter myDB){
        dbAdapter = myDB;
    }

    public String addCustomer(String name, String address, String emailAddress)
                                     throws CouldNotAddCustomer {

        Customer customer = new Customer(name, address, emailAddress);

        try {
            customer.setMyDb(dbAdapter);
            customer.record();
        }
        catch(CouldNotRecordCustomer e) {
            throw new CouldNotAddCustomer();
        }

        CreditCardPrinter.printCard(customer.getID(), customer.getName());

        return customer.getID();
    }

    public void listTransactions(String customerID, DataLister<Transaction> listener)
                                        throws NoSuchCustomer, CouldNotReadCustomerTable, CouldNotListTransactions {
        Customer customer;
        try {
            customer = this.dbAdapter.getCustomer(customerID);
        } catch(DBReadError E) {
            throw new CouldNotReadCustomerTable();
        }

        if(customer == null)
            throw new NoSuchCustomer();

        customer.setMyDb(dbAdapter);

        customer.pushTransactions(listener);

    }

    public Transaction computeTransaction(String customerID, double subTotal) throws NoSuchCustomer,
                                          CouldNotReadCustomerTable, CouldNotComputeTransaction {
        Customer customer;
        try {
            customer = this.dbAdapter.getCustomer(customerID);
        } catch(DBReadError E) {
            throw new CouldNotReadCustomerTable();
        }

        if(customer == null)
            throw new NoSuchCustomer();

        try {
            customer.setMyDb(dbAdapter);
            return customer.computePurchase(subTotal);
        } catch(DBReadError e) {
            throw new CouldNotComputeTransaction();
        }
    }

    public Transaction addPurchase(double subTotal, DataLister<Customer> onSuccess)
                                   throws NoSuchCustomer, CouldNotAddPurchase, ErrorScanningQRCard,
            CouldNotSendEmail, CouldNotReadCreditCard, CouldNotProcessCreditCard, CouldNotReadCustomerTable {
        String ID = QRCodeService.scanQRCode();
        if(ID.equals("ERR"))
            throw new ErrorScanningQRCard();
        else
            return addPurchase(ID, subTotal, onSuccess);
    }

    public Transaction addPurchase(String customerID, double subTotal, DataLister<Customer> onSuccess) throws NoSuchCustomer,
                                    CouldNotReadCustomerTable, CouldNotAddPurchase, CouldNotSendEmail,
                                    CouldNotReadCreditCard, CouldNotProcessCreditCard{
        Customer customer;
        try {
            customer = this.dbAdapter.getCustomer(customerID);
        } catch(DBReadError E) {
            throw new CouldNotReadCustomerTable();
        }

        if(customer == null)
            throw new NoSuchCustomer();

        try {
            customer.setMyDb(dbAdapter);
            return customer.addPurchase(subTotal, onSuccess);
        } catch(DBReadError e) {
            throw new CouldNotAddPurchase();
        }

    }

    public Customer getCustomerByQRCard() throws CouldNotReadCustomerTable,
                                                ErrorScanningQRCard, NoSuchCustomer {
        String ID = QRCodeService.scanQRCode();
        if(ID.equals("ERR"))
            throw new ErrorScanningQRCard();

        Customer customer;
        try {
            customer = this.dbAdapter.getCustomer(ID);
        } catch(DBReadError E) {
            throw new CouldNotReadCustomerTable();
        }

        if(customer == null)
            throw new NoSuchCustomer();

        customer.setMyDb(dbAdapter);

        return customer;
    }

    public Customer getCustomerByID(String ID) throws CouldNotReadCustomerTable, NoSuchCustomer {

        Customer customer;
        try {
            customer = this.dbAdapter.getCustomer(ID);
        } catch(DBReadError E) {
            throw new CouldNotReadCustomerTable();
        }

        if(customer == null)
            throw new NoSuchCustomer();

        customer.setMyDb(dbAdapter);

        return customer;
    }

    public void editCustomerDetails(String ID, String name, String billingAddress,
                                    String emailAddress) throws NoSuchCustomer,
                                    CouldNotReadCustomerTable, CouldNotUpdateCustomerDetails {
        Customer customer;
        try {
            customer = this.dbAdapter.getCustomer(ID);
        } catch(DBReadError E) {
            throw new CouldNotReadCustomerTable();
        }

        if(customer == null)
            throw new NoSuchCustomer();

        customer.setName(name);
        customer.setAddress(billingAddress);
        customer.setEmailAddress(emailAddress);

        try {
            this.dbAdapter.updateCustomer(customer);
        } catch(DBWriteError e) {
            throw new CouldNotUpdateCustomerDetails();
        }
    }

}