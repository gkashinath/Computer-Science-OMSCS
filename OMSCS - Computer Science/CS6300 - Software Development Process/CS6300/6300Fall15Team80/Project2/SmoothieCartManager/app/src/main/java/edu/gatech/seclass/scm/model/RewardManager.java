package edu.gatech.seclass.scm.model;

import android.database.sqlite.SQLiteDatabase;

import edu.gatech.seclass.scm.model.exceptions.*;

/**
 * Created by HARESH on 10/12/2015.
 */
public class RewardManager {

    //static DBAdapter myDb;
    private SQLiteDatabase database;
    private DBAdapter dbAdapter;

    static public final String GOLD_CODE = "APPLIED_GOLD";
    static public final String CREDIT_CODE = "CREDIT_EARNED";

    public RewardManager(DBAdapter myDb){
        dbAdapter = myDb;
    }

    public double getCreditThreshold() throws DBReadError {
        return this.dbAdapter.getPolicyCreditThreshold();
    }

    public void setCreditThreshold(double creditThreshold) throws DBWriteError {
        this.dbAdapter.setPolicyCreditThreshold(creditThreshold);
    }

    public double getGoldDiscountThreshold() throws DBReadError {
        return this.dbAdapter.getPolicyGoldDiscountThreshold();
    }

    public void setGoldDiscountThreshold(double goldDiscountThreshold) throws DBWriteError {
        this.dbAdapter.setPolicyGoldDiscountThreshold(goldDiscountThreshold);
    }

    public double getCreditPolicyAmount() throws DBReadError {
       return this.dbAdapter.getPolicyCreditAmount();
    }

    public void setCreditPolicyAmount(double policyAmount) throws DBWriteError {
        this.dbAdapter.setPolicyCreditAmount(policyAmount);
    }

    public double getGoldDiscountPercentage() throws DBReadError {
        return this.dbAdapter.getPolicyGoldPercentage();
    }

    public void setGoldDiscountPercentage(double percentage) throws DBWriteError {
        this.dbAdapter.setPolicyGoldPercentage(percentage);
    }

    static public String getCreditEmailSubject() {
        return "Credit applied";
    }

    static public String getCreditEmailMessage() {
        return "You earned $5.00 credit";
    }

    static public String getGoldEmailSubject() {
        return "Gold status earned";
    }

    static public String getGoldEmailMessage() {
        return "You earned gold status";
    }
}
