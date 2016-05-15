package edu.gatech.seclass.scm.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.gatech.seclass.scm.model.exceptions.*;

public final class DBAdapter extends SQLiteOpenHelper {

    //FOR THE COMMON DATABASE
    public static final String DATABASE_NAME = "smoothiecart.db";

    //FOR THE CUSTOMER TABLE
    public static final String CUSTOMER_TABLE_NAME = "customer_table";
    public static final String CUSTOMER_COL_1 = "ID";
    public static final String CUSTOMER_COL_2 = "NAME";
    public static final String CUSTOMER_COL_3 = "BILLING_ADDRESS";
    public static final String CUSTOMER_COL_4 = "EMAIL_ADDRESS";
    public static final String CUSTOMER_COL_5 = "UNIQUE_ID";
    public static final String CUSTOMER_COL_6 = "TRANSACTION_LIST";
    public static final String CUSTOMER_COL_7 = "GOLD_STATUS";
    public static final String CUSTOMER_COL_8 = "CREDIT_VALUE";
    public static final String CUSTOMER_COL_9 = "CREDIT_EXP_DATE";
    public static final String CUSTOMER_COL_10 = "LAST_PURCHASE_DATE";
    public static final String CUSTOMER_COL_11 = "TOTAL_PURCHASE_SINCE_JAN";

    //FOR THE TRANSACTION TABLE
    public static final String TRANSACTION_TABLE_NAME = "transaction_table";
    public static final String TRANSACTION_COL_1 = "ID";
    public static final String TRANSACTION_COL_2 = "DATE";
    public static final String TRANSACTION_COL_3 = "RAW_PURCHASE_PRICE";
    public static final String TRANSACTION_COL_4 = "REWARDS_APPLIED";
    public static final String TRANSACTION_COL_5 = "REWARD_VALUE_APPLIED";
    public static final String TRANSACTION_COL_6 = "ACTUAL_PURCHASE_PRICE";

    //FOR THE REWARD TABLE
    public static final String POLICY_TABLE_NAME = "policy_table";
    public static final String POLICY_COL_1 = "ID";
    public static final String POLICY_COL_2 = "CREDIT_THRESHOLD";
    public static final String POLICY_COL_3 = "GOLD_STATUS_THRESHOLD";
    public static final String POLICY_COL_4 = "CREDIT_AMOUNT";
    public static final String POLICY_COL_5 = "GOLD_PERCENTAGE";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            //Create the Customer Table
            db.execSQL("create table " + CUSTOMER_TABLE_NAME + " (" +
                    CUSTOMER_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CUSTOMER_COL_2 + " TEXT," +
                    CUSTOMER_COL_3 + " TEXT," +
                    CUSTOMER_COL_4 + " TEXT," +
                    CUSTOMER_COL_5 + " TEXT," +
                    CUSTOMER_COL_6 + " TEXT," +
                    CUSTOMER_COL_7 + " INT," +
                    CUSTOMER_COL_8 + " TEXT," +
                    CUSTOMER_COL_9 + " TEXT," +
                    CUSTOMER_COL_10 + " TEXT," +
                    CUSTOMER_COL_11 + " TEXT)");

            //Create the Transaction Table
            db.execSQL("create table " + TRANSACTION_TABLE_NAME + " (" +
                    TRANSACTION_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TRANSACTION_COL_2 + " TEXT," +
                    TRANSACTION_COL_3 + " REAL," +
                    TRANSACTION_COL_4 + " TEXT," +
                    TRANSACTION_COL_5 + " REAL," +
                    TRANSACTION_COL_6 + " REAL)");

            //Create the Transaction Table
            db.execSQL("create table " + POLICY_TABLE_NAME + " (" +
                    POLICY_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    POLICY_COL_2 + " REAL," +
                    POLICY_COL_3 + " REAL," +
                    POLICY_COL_4 + " REAL," +
                    POLICY_COL_5 + " REAL)");

            //Populate the database with the initial values for the Policy
            ContentValues contentValues = new ContentValues();
            contentValues.put(POLICY_COL_1, 0);
            contentValues.put(POLICY_COL_2, 50);
            contentValues.put(POLICY_COL_3, 500);
            contentValues.put(POLICY_COL_4, 5);
            contentValues.put(POLICY_COL_5, 5);

            //Attempt to write the entry to the database
            db.insert(POLICY_TABLE_NAME, null, contentValues);

            //Populate the database with test data supplied by assignment

            //ENTRY 1
            //Create entry for database
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(CUSTOMER_COL_2, "Ralph Hapschatt");//from assignment
            contentValues1.put(CUSTOMER_COL_3, "1234 Elm Lane");//dummy input
            contentValues1.put(CUSTOMER_COL_4, "ralph@gmail.com");//dummy input
            contentValues1.put(CUSTOMER_COL_5, "b53b7c86ffeeaddbbe352f1f4dcd8e1a");//from assignment
            contentValues1.put(CUSTOMER_COL_6, "");//dummy input
            contentValues1.put(CUSTOMER_COL_7, 0);//dummy input
            contentValues1.put(CUSTOMER_COL_8, 0);//dummy input
            contentValues1.put(CUSTOMER_COL_9, "08/01/2016");//dummy input
            contentValues1.put(CUSTOMER_COL_10, "08/01/2015");//dummy input
            contentValues1.put(CUSTOMER_COL_11, 0);//dummy input

            //Attempt to write the entry tot he database
            db.insert(CUSTOMER_TABLE_NAME, null, contentValues1);

            //ENTRY 2
            //Create entry for database
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(CUSTOMER_COL_2, "Betty Monroe");
            contentValues2.put(CUSTOMER_COL_3, "1234 Elm Lane");//dummy input
            contentValues2.put(CUSTOMER_COL_4, "Betty@gmail.com");//dummy input
            contentValues2.put(CUSTOMER_COL_5, "b6acb59441af4ea13129d8373df8145e");
            contentValues2.put(CUSTOMER_COL_6, "");//dummy input
            contentValues2.put(CUSTOMER_COL_7, 0);//dummy input
            contentValues2.put(CUSTOMER_COL_8, 0);//dummy input
            contentValues2.put(CUSTOMER_COL_9, "05/01/2016");//dummy input
            contentValues2.put(CUSTOMER_COL_10, "05/01/2015");//dummy input
            contentValues2.put(CUSTOMER_COL_11, 0);//dummy input

            //Attempt to write the entry tot he database
            db.insert(CUSTOMER_TABLE_NAME, null, contentValues2);

            //ENTRY 3
            //Create entry for database
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(CUSTOMER_COL_2, "Everett Scott");
            contentValues3.put(CUSTOMER_COL_3, "1234 Elm Lane");//dummy input
            contentValues3.put(CUSTOMER_COL_4, "Everett@gmail.com");//dummy input
            contentValues3.put(CUSTOMER_COL_5, "f184cd0f0e056d4c58c4b0264e5a6bcc");
            contentValues3.put(CUSTOMER_COL_6, "");//dummy input
            contentValues3.put(CUSTOMER_COL_7, 0);//dummy input
            contentValues3.put(CUSTOMER_COL_8, 0);//dummy input
            contentValues3.put(CUSTOMER_COL_9, "10/01/2016");//dummy input
            contentValues3.put(CUSTOMER_COL_10, "10/01/2015");//dummy input
            contentValues3.put(CUSTOMER_COL_11, 0);//dummy input

            //Attempt to write the entry tot he database
            db.insert(CUSTOMER_TABLE_NAME, null, contentValues3);

        }
        catch (Exception e){
            //This happens on every launch that isn't the first one.
            Log.w("ISSUE", "Error while creating db: " + e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + POLICY_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Reads the customer table and constructs a single Customer object using getters and setters.
     * @param ID The customer unique ID
     * @return The Customer object if successful, or null if there is no such customer
     * @throws DBReadError There was an error reading from the customer table
     */
    public Customer getCustomer(String ID) throws DBReadError {

        try {

            SQLiteDatabase db = getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + CUSTOMER_TABLE_NAME + " WHERE " + CUSTOMER_COL_5 + " = '" + ID +"'", null);

            if (res.getCount() == 0){
                return null;
            }
            else {
                double credit = 0;

                while (res.moveToNext()) {
                    Customer customer = new Customer(res.getString(1), res.getString(2), res.getString(3));

                    customer.setID(res.getString(4));

                    //get the string from the database and convert to arraylist
                    String transactionListString = res.getString(5);
                    //ArrayList<String> transactionList = (ArrayList) Arrays.asList(transactionListString.split("\\s* \\s*"));
                    List<String> transactionList = Arrays.asList(transactionListString.split("\\s+"));
                    customer.setTransactions(transactionList);

                    //Extract Gold Status Int and convert to Boolean
                    boolean goldStatusBool;
                    int goldStatusInt;
                    goldStatusInt = res.getInt(6);
                    if (goldStatusInt == 1){
                        goldStatusBool = true;
                    }
                    else goldStatusBool = false;
                    customer.setGoldStatus(goldStatusBool);

                    customer.setCredit(Double.parseDouble(res.getString(7)));

                    //convert String to Date
                    String CreditExpiryDateString = res.getString(8);
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date transactionDate = df.parse(CreditExpiryDateString);
                    customer.setCreditExpiryDate(transactionDate);

                    //convert String to Date
                    String LastPurchaseCalendarDateString = res.getString(9);
                    DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                    Date LastPurchaseCalendarDate = df1.parse(LastPurchaseCalendarDateString);
                    customer.setLastPurchaseCalendarDate(LastPurchaseCalendarDate);

                    customer.setDiscountTotalSinceJan(Double.parseDouble(res.getString(10)));

                    return customer;
                }

                //TODO: Get rid of the next 2 lines
                Customer cus = new Customer("a","a","a");
                return cus;

            }

        }
        catch (Exception e){
            e.printStackTrace();
            throw new DBReadError();
        }
    }

    /**
     * Reads the Customer, Transaction and link tables, builds a list of Customer objects and
     * returns it.
     * @return A String containing a list of Customer objects
     * @throws DBReadError
     */
    public String getCustomers() throws DBReadError {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + CUSTOMER_TABLE_NAME, null);
            if (res.getCount() == 0) {
                return "";
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID: " + res.getString(0) + "\n");
                buffer.append("NAME: " + res.getString(1) + "\n");
                buffer.append("BILLING_ADDRESS: " + res.getString(2) + "\n");
                buffer.append("EMAIL_ADDRESS: " + res.getString(3) + "\n");
                buffer.append("UNIQUE_ID: " + res.getString(4) + "\n");
                buffer.append("TRANSACTION_LIST: " + res.getString(5) + "\n");
                buffer.append("GOLD_STATUS: " + res.getInt(6) + "\n");
                buffer.append("CREDIT_VALUE: " + res.getString(7) + "\n");
                buffer.append("CREDIT_EXP_DATE: " + res.getString(8) + "\n");
                buffer.append("LAST_PURCHASE_DATE: " + res.getString(9) + "\n");
                buffer.append("TOTAL_PURCHASE_SINCE_JAN: " + res.getString(10) + "\n\n");
            }

            return buffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new DBReadError();
        }
    }

    /**
     * Adds a customer's data to the Customer table. All fields are used EXCEPT the ID, which is
     * uniquely generated by SQLLite and returned on success.
     * @return The unique ID of the added customer.
     * @throws DBWriteError
     */
    public String addCustomer(Customer customer) throws DBWriteError {

        //Get database
        SQLiteDatabase db = getWritableDatabase();

        //Generate Unique ID
        UUID uniqueID_UUID = UUID.randomUUID();
        String uniqueID = uniqueID_UUID.toString();

        //Create entry for database
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_COL_2, customer.getName());
        contentValues.put(CUSTOMER_COL_3,customer.getAddress());
        contentValues.put(CUSTOMER_COL_4,customer.getEmailAddress());
        contentValues.put(CUSTOMER_COL_5,uniqueID);
        contentValues.put(CUSTOMER_COL_6,"");  //This is an empty transaction list
        contentValues.put(CUSTOMER_COL_7,0); //This is 0 for a new customer
        contentValues.put(CUSTOMER_COL_8,0);  //This is 0 for a new customer
        contentValues.put(CUSTOMER_COL_9,"01/01/2010"); //default date
        contentValues.put(CUSTOMER_COL_10,"01/02/2010");  //default date
        contentValues.put(CUSTOMER_COL_11,0);  //This is 0 for a new customer

        //Attempt to write the entry tot he database
        long result = db.insert(CUSTOMER_TABLE_NAME, null, contentValues);
        if (result == -1)
            throw new DBWriteError();
        else
            return uniqueID;
    }

    /**
     * Reads the transaction table and constructs a single Transaction object using getters and setters.
     * @param ID The transaction ID
     * @return The transaction object if successful, or null if there is no such transaction
     * @throws DBReadError There was an error reading from the transaction table
     */
    public Transaction getTransaction(String ID) throws DBReadError {
        try {

            SQLiteDatabase db = getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM transaction_table WHERE ID = " + "'" + ID + "'", null);

            Transaction transaction = new Transaction();

            if (res.getCount() == 0) {
                return null;
            } else {
                while (res.moveToNext()) {
                    //convert String to Date
                    String transactionDateString = res.getString(1);
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date transactionDate = df.parse(transactionDateString);
                    transaction.setDate(transactionDate);

                    transaction.setID(ID);

                    transaction.setSubTotal(res.getDouble(2));

                    //get the string from the database and convert to arraylist
                    String DiscountsString = res.getString(3);
                    List<String> transactionList = Arrays.asList(DiscountsString.split("\\s+"));
                    transaction.setDiscounts(transactionList);

                    transaction.setDiscountTotal(res.getDouble(4));
                    transaction.setTotal(res.getDouble(5));

                    return transaction;
                }
            }

            //TODO: Get rid of the next 2 lines
            Transaction tran = new Transaction();
            return tran;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBReadError();
        }
    }
//////////////--------------------------///////////////////////////
//        try {
//
//            SQLiteDatabase db = getWritableDatabase();
//            Cursor res = db.rawQuery("SELECT * FROM " + CUSTOMER_TABLE_NAME + " WHERE " + CUSTOMER_COL_5 + " = '" + ID +"'", null);
//
//            if (res.getCount() == 0){
//                return null;
//            }
//            else {
//                double credit = 0;
//
//                while (res.moveToNext()) {
//                    Customer customer = new Customer(res.getString(1), res.getString(2), res.getString(3));
//
//                    customer.setID(res.getString(4));
//
//                    //get the string from the database and convert to arraylist
//                    String transactionListString = res.getString(5);
//                    //ArrayList<String> transactionList = (ArrayList) Arrays.asList(transactionListString.split("\\s* \\s*"));
//                    List<String> transactionList = Arrays.asList(transactionListString.split("\\s+"));
//                    customer.setTransactions(transactionList);
//
//                    //Extract Gold Status Int and convert to Boolean
//                    boolean goldStatusBool;
//                    int goldStatusInt;
//                    goldStatusInt = res.getInt(6);
//                    if (goldStatusInt == 1){
//                        goldStatusBool = true;
//                    }
//                    else goldStatusBool = false;
//                    customer.setGoldStatus(goldStatusBool);
//
//                    customer.setCredit(Double.parseDouble(res.getString(7)));
//
//                    //convert String to Date
//                    String CreditExpiryDateString = res.getString(8);
//                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//                    Date transactionDate = df.parse(CreditExpiryDateString);
//                    customer.setCreditExpiryDate(transactionDate);
//
//                    //convert String to Date
//                    String LastPurchaseCalendarDateString = res.getString(9);
//                    DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
//                    Date LastPurchaseCalendarDate = df1.parse(LastPurchaseCalendarDateString);
//                    customer.setLastPurchaseCalendarDate(LastPurchaseCalendarDate);
//
//                    customer.setDiscountTotalSinceJan(Double.parseDouble(res.getString(10)));
//
//                    return customer;
//                }
//
//                //TODO: Get rid of the next 2 lines
//                Customer cus = new Customer("a","a","a");
//                return cus;
//
//            }
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            throw new DBReadError();
//        }

//    }

    /**
     * Adds a transaction's data to the Transaction table and create a link in the database to the
     * customer ID. All fields are used EXCEPT the ID, which is uniquely generated by SQLLite and
     * returned on success.
     * @return The unique ID of the added transaction.
     * @throws DBWriteError
     */
    public String addTransaction(Transaction transaction) throws DBWriteError {

        //get Database
        SQLiteDatabase db = getWritableDatabase();

        //Create entry for database
        ContentValues contentValues = new ContentValues();

        //convert Date to String
        try {
            Date transactionDate = transaction.getDate();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String transactionDateString = df.format(transactionDate);
            contentValues.put(TRANSACTION_COL_2, transactionDateString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        contentValues.put(TRANSACTION_COL_3,transaction.getSubTotal());

        //convert list of discounts to string
        try {
            List<String> discountsApplied = transaction.getDiscounts();
            StringBuilder discountsAppliedString = new StringBuilder();
            for (String s : discountsApplied)
                discountsAppliedString.append(s + " ");
            contentValues.put(TRANSACTION_COL_4,discountsAppliedString.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        contentValues.put(TRANSACTION_COL_5,transaction.getDiscountTotal());
        contentValues.put(TRANSACTION_COL_6,transaction.getTotal());

        //Attempt to write the entry tot he database
        long result = db.insert(TRANSACTION_TABLE_NAME, null, contentValues);

        //Extract ID assigned to last transaction
        //int uniqueID = (int) result;
        String uniqueID = String.valueOf(result);

        if (result == -1)
            throw new DBWriteError();
        else
            return uniqueID;

        //Add transaction ID to given Customer
    }

    /**
     * Updates an existing customer's data on the Customer table, using ALL fields (including ID).
     * @return boolean.  True if successful, False if fail
     * @throws DBWriteError
     */
    public boolean updateCustomer(Customer customer) throws DBWriteError {

        //Get database
        SQLiteDatabase db = getWritableDatabase();

        //Create entry for database
        ContentValues contentValues = new ContentValues();
//        contentValues.put(CUSTOMER_COL_2,customer.getFirstName());
        contentValues.put(CUSTOMER_COL_3,customer.getAddress());
        contentValues.put(CUSTOMER_COL_4,customer.getEmailAddress());
        contentValues.put(CUSTOMER_COL_5,customer.getID());

        //Get the transaction list and convert from arraylist to string
        List<String> transactionList = customer.getTransactions();
        StringBuilder transactionListString = new StringBuilder();
        for (String s : transactionList)
            transactionListString.append(s + " ");
        contentValues.put(CUSTOMER_COL_6,transactionListString.toString());

        //Convert gold statuss boolean to int
        boolean goldStatusBool;
        int goldStatusInt;
        goldStatusBool = customer.getGoldStatus();
        if (goldStatusBool){
            goldStatusInt = 1;
        }
        else goldStatusInt = 0;
        contentValues.put(CUSTOMER_COL_7, goldStatusInt);

        contentValues.put(CUSTOMER_COL_8,customer.getCredit());

        //Get the date and convert it to a string for the database
        Date creditExpiryDate = customer.getCreditExpiryDate();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String creditExpiryDateString = df.format(creditExpiryDate);
        contentValues.put(CUSTOMER_COL_9,creditExpiryDateString);

        //Get the date and convert it to a string for the database
        Date lastPurchaseCalendarDate = customer.getLastPurchaseCalendarDate();
        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        String lastPurchaseCalendarDateString = df1.format(lastPurchaseCalendarDate);
        contentValues.put(CUSTOMER_COL_10, lastPurchaseCalendarDateString);

        contentValues.put(CUSTOMER_COL_11,customer.getDiscountTotalSinceJan());

        //long result = db.update(POLICY_TABLE_NAME, contentValues, "ID = ?",new String[] {"0"});
        long result = db.update(CUSTOMER_TABLE_NAME, contentValues, "UNIQUE_ID = ?",new String[] {customer.getID()});
        if (result == -1)
            throw new DBWriteError();
        else
            return true;

    }

    /**
     * Deletes a transaction from the transaction table.
     * @param ID The ID of the transaction to delete.
     * @return boolean.  True if successful, False if fail
     * @throws DBWriteError
     */
    public boolean deleteTransaction(String ID) throws DBWriteError {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TRANSACTION_TABLE_NAME,"ID = ?",new String[] {ID});

        if (result == 0)
            throw new DBWriteError();
        else
            return true;
    }

//TODO: Remove discount table operations
//    /**
//     * Reads the discount table and constructs a single Discount object using getters and setters.
//     * @param ID The discount ID
//     * @return The Discount object if successful, or null if there is no such discount
//     * @throws DBReadError There was an error reading from the discount table
//     */
//
//    public Discount getDiscount(String ID) throws DBReadError {
//        return null;
//    }
//
//    /**
//     * Reads the Reward table, build a list of rewards and returns them.
//     * @return A String containing a list of Customer objects with Transaction objects attached.
//     * @throws DBReadError
//     */
//    public String getDiscounts() throws DBReadError {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from " + REWARD_TABLE_NAME, null);
//        if (res.getCount() == 0){
//            return "";
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            buffer.append("ID: "+res.getString(0)+"\n");
//            buffer.append("DISCOUNT_PERCENT: "+res.getString(1)+"\n");
//            buffer.append("CREDIT_AMOUNT: "+res.getString(2)+"\n\n");
//        }
//
//        return buffer.toString();
//    }
//
//    /**
//     * Adds a reward to the table. All fields are used EXCEPT the ID, which is
//     * uniquely generated by SQLLite and returned on success.
//     * @return The unique ID of the added reward.
//     * @throws DBWriteError
//     */
//    public String addDiscount(Discount discount) throws DBWriteError {
//        //throw new DBWriteError();
//
//        //get Database
//        SQLiteDatabase db = getWritableDatabase();
//
//        String CurrentDate = "Current Date"; //TODO: get the current data
//
//        //Create entry for database
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(REWARD_COL_2,discount.getPercentage());
//        //contentValues.put(TRANSACTION_COL_3,discount.getCreditValue());//TODO: the Discount class needs a Credit Value
//
//        //Extract ID assigned to last transaction
//        String uniqueID = "fake_unique_ID";
//
//        //Attempt to write the entry tot he database
//        long result = db.insert(REWARD_TABLE_NAME, null, contentValues);
//        if (result == -1)
//            throw new DBWriteError();
//        else
//            return uniqueID;
//    }

    // TODO: Add the store policy persistence functions - Haresh



    /**
     * Reads the credit threshold from the policy table.
     * @return The credit threshold
     * @throws DBReadError If there is an error reading from the database
     */
    public double getPolicyCreditThreshold() throws DBReadError {
        try {
            //db = new SQLiteDatabase();
            SQLiteDatabase db = getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + POLICY_TABLE_NAME + " WHERE " + POLICY_COL_1 + " = " + "0", null);

            if (res.getCount() == 0){
                return 0;
            }
            else {
                double credit = 0;

                while (res.moveToNext()) {
                    credit = res.getDouble(1);
                }
                return credit;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new DBReadError();
        }
    }

    /**
     * Writes the new credit threshold to the policy table.
     * @param threshold The new credit threshold
     * @throws DBWriteError If there is an error writing to the table
     */
    public void setPolicyCreditThreshold(double threshold) throws DBWriteError {

        //Get database
        SQLiteDatabase db = getWritableDatabase();

        //Create entry for database
        ContentValues contentValues = new ContentValues();

        contentValues.put(POLICY_COL_2,threshold);

        long result = db.update(POLICY_TABLE_NAME, contentValues, "ID = ?",new String[] {"0"});
        if (result == -1)
            throw new DBWriteError();
    }

    /**
     * Reads the gold discount threshold from the policy table.
     * @return The gold discount threshold
     * @throws DBReadError If there is an error reading from the database
     */
    public double getPolicyGoldDiscountThreshold() throws DBReadError {
        try {
            //db = new SQLiteDatabase();
            SQLiteDatabase db = getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + POLICY_TABLE_NAME + " WHERE " + POLICY_COL_1 + " = " + "0", null);

            if (res.getCount() == 0){
                return 0;
            }
            else {
                double credit = 0;

                while (res.moveToNext()) {
                    credit = res.getDouble(2);
                }
                return credit;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new DBReadError();
        }
    }

    /**
     * Writes the new gold discount threshold to the policy table.
     * @param threshold The new gold discount threshold
     * @throws DBWriteError If there is an error writing to the table
     */
    public void setPolicyGoldDiscountThreshold(double threshold) throws DBWriteError {
        //Get database
        SQLiteDatabase db = getWritableDatabase();

        //Create entry for database
        ContentValues contentValues = new ContentValues();

        contentValues.put(POLICY_COL_3,threshold);

        long result = db.update(POLICY_TABLE_NAME, contentValues, "ID = ?",new String[] {"0"});
        if (result == -1)
            throw new DBWriteError();
    }

    /**
     * Reads the credit amount from the policy table.
     * @return The credit amount
     * @throws DBReadError If there is an error reading from the database
     */
    public double getPolicyCreditAmount() throws DBReadError {
        try {
            //db = new SQLiteDatabase();
            SQLiteDatabase db = getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + POLICY_TABLE_NAME + " WHERE " + POLICY_COL_1 + " = " + "0", null);

            if (res.getCount() == 0){
                return 0;
            }
            else {
                double credit = 0;

                while (res.moveToNext()) {
                    credit = res.getDouble(3);
                }
                return credit;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new DBReadError();
        }
    }

    /**
     * Writes the new credit amount to the policy table.
     * @param amount The new credit amount
     * @throws DBWriteError If there is an error writing to the table
     */
    public void setPolicyCreditAmount(double amount) throws DBWriteError {
        //Get database
        SQLiteDatabase db = getWritableDatabase();

        //Create entry for database
        ContentValues contentValues = new ContentValues();

        contentValues.put(POLICY_COL_4,amount);

        long result = db.update(POLICY_TABLE_NAME, contentValues, "ID = ?",new String[] {"0"});
        if (result == -1)
            throw new DBWriteError();
    }

    /**
     * Reads the gold discount percentage from the policy table.
     * @return The gold discount percentage
     * @throws DBReadError If there is an error reading from the database
     */
    public double getPolicyGoldPercentage() throws DBReadError {
        try {
            //db = new SQLiteDatabase();
            SQLiteDatabase db = getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + POLICY_TABLE_NAME + " WHERE " + POLICY_COL_1 + " = " + "0", null);

            if (res.getCount() == 0){
                return 0;
            }
            else {
                double credit = 0;

                while (res.moveToNext()) {
                    credit = res.getDouble(4);
                }
                return credit;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new DBReadError();
        }
    }

    /**
     * Writes the new gold discount percentage to the policy table.
     * @param percentage The new gold discount percentage
     * @throws DBWriteError If there is an error writing to the table
     */
    public void setPolicyGoldPercentage(double percentage) throws DBWriteError {
        //Get database
        SQLiteDatabase db = getWritableDatabase();

        //Create entry for database
        ContentValues contentValues = new ContentValues();

        contentValues.put(POLICY_COL_5,percentage);

        long result = db.update(POLICY_TABLE_NAME, contentValues, "ID = ?",new String[] {"0"});
        if (result == -1)
            throw new DBWriteError();
    }
}
