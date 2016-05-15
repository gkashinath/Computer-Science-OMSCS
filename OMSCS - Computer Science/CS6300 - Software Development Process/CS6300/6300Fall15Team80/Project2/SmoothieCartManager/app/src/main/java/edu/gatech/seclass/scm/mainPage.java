package edu.gatech.seclass.scm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.DBAdapter;
import edu.gatech.seclass.scm.model.Transaction;

public class mainPage extends AppCompatActivity {
    Button bMCust;
    Button bMTrans;
    Button bMReward;
    Button bMTestDB;
    DBAdapter myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        myDb = new DBAdapter(this);

        bMCust = (Button)findViewById(R.id.bCustomerMain);
        bMCust.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), customerMain.class);
                startActivity(id);
            }
        });

        bMTrans = (Button)findViewById(R.id.bTransactionMain);
        bMTrans.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), transactionMain.class);
                startActivity(id);
            }
        });

        bMReward = (Button)findViewById(R.id.bRewardsMain);
        bMReward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), rewardsGetSet.class);
                startActivity(id);
            }
        });
//        bMTestDB = (Button)findViewById(R.id.testDBbutton);
//        bMTestDB.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Customer customer1 = new Customer("George Stephonopolis","address","email");
//                myDb.addCustomer(customer1);
//                Transaction transaction1 = new Transaction();
//                transaction1.setTotal(12345);
//                myDb.addTransaction(transaction1);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
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
}
