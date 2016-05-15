package edu.gatech.seclass.scm;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.CustomerManager;
import edu.gatech.seclass.scm.model.DBAdapter;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.model.exceptions.CouldNotListTransactions;
import edu.gatech.seclass.scm.model.exceptions.CouldNotReadCustomerTable;
import edu.gatech.seclass.scm.model.exceptions.DBReadError;
import edu.gatech.seclass.scm.model.exceptions.ErrorScanningQRCard;
import edu.gatech.seclass.scm.model.exceptions.NoSuchCustomer;
import edu.gatech.seclass.scm.utils.DataLister;

public class customerPrintTrans extends AppCompatActivity {

    class TransactionListener implements DataLister<Transaction> {
        public void push(Transaction t)
        {
            // Add code to push transaction to list view
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_print_trans);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_print_trans, menu);
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
    }

    public void handleCustomerGet(android.view.View view){

        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        Customer customer;

        try {
            customer = customerManager.getCustomerByQRCard();
        } catch(ErrorScanningQRCard e) {
            Toast.makeText(getApplicationContext(), "Error scanning QR card",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer does not exist.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.print("test");
        TextView txtNAME = (TextView) findViewById(R.id.editText5);//Name
        TextView txtCUSTOMERID = (TextView) findViewById(R.id.editText6);//CustomerID000000000000000000


        txtNAME.setText(customer.getName());
        txtCUSTOMERID.setText(customer.getID());

    }

    public void handlePrintTrans(android.view.View view) {

        DBAdapter myDb = new DBAdapter(this);

        CustomerManager cm = new CustomerManager(new DBAdapter(this));

        TransactionListener tr = new TransactionListener();
        TextView txtCUSTOMERID = (TextView) findViewById(R.id.editText6);//CustomerID
        TextView txtTRANSACTIONLIST = (TextView) findViewById(R.id.tPrintTrans);//CustomerID

//
//        try {
//            cm.listTransactions(txtCUSTOMERID.getText().toString(), tr);
//        } catch (CouldNotReadCustomerTable e) {
//            Toast.makeText(getApplicationContext(), "Error reading customer info from database.",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        } catch (NoSuchCustomer e) {
//            Toast.makeText(getApplicationContext(), "Customer does not exist.",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        } catch (CouldNotListTransactions e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(),
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }

        try {
            Customer customer2 = cm.getCustomerByID(txtCUSTOMERID.getText().toString());
            List<String> transactions = customer2.getTransactions();
            Transaction t = new Transaction();
            StringBuffer buffer = new StringBuffer();

            for (String x : transactions) {
                if (!x.equals("")) {
                    t = myDb.getTransaction(x);
                    buffer.append("ID: " + t.getID() + "\n");
                    Date transactionDate = t.getDate();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String transactionDateString = df.format(transactionDate);
                    buffer.append("Date: " + transactionDateString + "\n");
                    //buffer.append("Raw Purchase Price: " + t.getTotal() + "\n");

                    List<String> discountsApplied = t.getDiscounts();
                    StringBuilder discountsAppliedString = new StringBuilder();
                    for (String s : discountsApplied)
                        discountsAppliedString.append(s + " ");
                    double amountBeforeDiscount =  t.getSubTotal() + t.getDiscountTotal();
                    buffer.append("Amount before discount: " + amountBeforeDiscount + "\n");
                    buffer.append("Rewards Applied: " + discountsAppliedString + "\n");
                    buffer.append("Discount Applied: " + String.format("%.2f",t.getDiscountTotal()) + "\n");
                    buffer.append("Final Purchase Price: " + t.getSubTotal() + "\n");
                    buffer.append("--------------------------------" + "\n");
                }
            }
            String listOfTransactions = buffer.toString();
            txtTRANSACTIONLIST.setText(listOfTransactions);

        }
        catch(CouldNotListTransactions e){
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }








    }
}
