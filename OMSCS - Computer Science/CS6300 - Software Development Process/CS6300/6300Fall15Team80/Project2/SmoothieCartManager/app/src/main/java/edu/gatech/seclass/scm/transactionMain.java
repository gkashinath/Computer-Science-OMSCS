package edu.gatech.seclass.scm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.CustomerManager;
import edu.gatech.seclass.scm.model.DBAdapter;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.model.exceptions.*;
import edu.gatech.seclass.scm.utils.DataLister;

public class transactionMain extends AppCompatActivity {

    class AppliedTransaction implements DataLister<Customer> {
        public void push(Customer c) {
            Toast.makeText(getApplicationContext(), "Applied transaction to customer " +
                            c.getName() + ", ID " + c.getID(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    } public void handleCustomerGet(android.view.View view){
        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        EditText txtNAME = (EditText) findViewById(R.id.editText25);//Name
        EditText txtCUSTOMERID = (EditText) findViewById(R.id.editText8);//CustomerID

        Customer customer;

        try {
            customer = customerManager.getCustomerByQRCard();
        } catch(CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer does not exist.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(ErrorScanningQRCard e) {
            Toast.makeText(getApplicationContext(), "Error scanning QR card.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        txtCUSTOMERID.setText(customer.getID());
        txtNAME.setText(customer.getName());
    }

    public void CalculateFinalPrice(android.view.View view){
        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        TextView priceView = (TextView) findViewById(R.id.editText10);
        EditText txtCUSTOMERID = (EditText) findViewById(R.id.editText8);//CustomerID

        double price;

        try {
            price = Double.parseDouble(priceView.getText().toString());
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Price field is not a valid number",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Transaction t;

        try {
            t = customerManager.computeTransaction(txtCUSTOMERID.getText().toString(),price);
        } catch (CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer doesn't exist",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (CouldNotComputeTransaction e) {
            Toast.makeText(getApplicationContext(), "Error computing transaction",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(Exception e) {
            Log.d("transactionMain", e.getMessage(), e);
            return;
        }

        TextView dateView = (TextView) findViewById(R.id.editText9);
        TextView discountView = (TextView) findViewById(R.id.editText11);
        TextView discountTypeView = (TextView) findViewById(R.id.editText12);
        TextView finalPriceView = (TextView) findViewById(R.id.editText13);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        dateView.setText(df.format(t.getDate()));

        Double subTotal = new Double(t.getSubTotal());
        priceView.setText(subTotal.toString());

        Double discountTotal = new Double(t.getDiscountTotal());
        discountView.setText(discountTotal.toString());

        String discountType = "";
        for(String discount : t.getDiscounts())
            discountType += " " + discount;

        discountTypeView.setText(discountType);

        Double total = new Double(t.getTotal());
        finalPriceView.setText(total.toString());
    }

    public void applyTransaction(android.view.View view){
        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));
        TextView priceView = (TextView) findViewById(R.id.editText10);
        EditText txtsubTotal = (EditText) findViewById(R.id.editText13);
        EditText txtcustomerID = (EditText) findViewById(R.id.editText8);

        double finalprice;

        try {
            finalprice = Double.parseDouble(txtsubTotal.getText().toString());
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Price field is not a valid number",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String customerID = txtcustomerID.getText().toString();

        Transaction t;

        try {
            t = customerManager.addPurchase(customerID,finalprice, new AppliedTransaction());
        } catch (ErrorScanningQRCard e) {
            Toast.makeText(getApplicationContext(), "Error scanning QR card",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer doesn't exist",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (CouldNotReadCreditCard e) {
            Toast.makeText(getApplicationContext(), "Error reading customer credit card",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (CouldNotProcessCreditCard e) {
            Toast.makeText(getApplicationContext(), "Could not process credit card",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (CouldNotAddPurchase e) {
            Toast.makeText(getApplicationContext(), "Could not add purchase to database",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch (CouldNotSendEmail e) {
            Toast.makeText(getApplicationContext(), "Transaction applied, but email not sent; " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(Exception e) {
            Log.d("transactionMain", e.getMessage(), e);
            return;
        }

    }

}
