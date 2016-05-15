package edu.gatech.seclass.scm.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.gatech.seclass.scm.model.exceptions.*;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;

/**
 * Created by HARESH on 10/12/2015.
 */
public class Transaction {

    DBAdapter myDb;

    private String ID;
    private Date date;
    private double subTotal;
    private List<String> discountsApplied;
    private double discountTotal;
    private double credit;
    private double total;

    public void setMyDb(DBAdapter myDb) {
        this.myDb = myDb;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public List<String> getDiscounts() {
        return discountsApplied;
    }

    public void setDiscounts(List<String> discountList) {
        this.discountsApplied = discountList;
    }

    public double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    String apply(Customer customer) throws CouldNotAddPurchase, CouldNotReadCreditCard,
            CouldNotProcessCreditCard {
        date = new Date();
        total = (subTotal - discountTotal) - credit;

        if (total > 0) {
            String ccInfo = CreditCardService.readCard();
            if(ccInfo.equals("ERR"))
                throw new CouldNotReadCreditCard();
            String[] ccfields = ccInfo.split("#");
            try {
                DateFormat df = new SimpleDateFormat("MMddyyyy");
                if(!PaymentService.processTransaction(ccfields[0], ccfields[1], ccfields[2],  df.parse(ccfields[3]), ccfields[4], total))
                    throw new RuntimeException();
            } catch(Exception e) {
                throw new CouldNotProcessCreditCard();
            }
        }


        try {
            ID = myDb.addTransaction(this);
        } catch (DBWriteError e) {
            throw new CouldNotAddPurchase();
        }
       return ID;
    }
}
