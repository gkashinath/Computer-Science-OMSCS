package edu.gatech.seclass.scm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.scm.model.DBAdapter;
import edu.gatech.seclass.scm.model.RewardManager;

public class rewardsSet extends AppCompatActivity {
//    RewardManager myRM = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if (myRM == null) myRM = new RewardManager();

        // TODO: Update this code. Sorry for modifying it. - Haresh



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_set);

        EditText txtCA = (EditText) findViewById(R.id.sRCreditAmountTxt);
        EditText txtCT = (EditText) findViewById(R.id.sRCreditThresholdTxt);
        EditText txtDP = (EditText) findViewById(R.id.sRDiscountPerTxt);
        EditText txtGST = (EditText) findViewById(R.id.sRGoldStatusThresholdTxt);

        RewardManager rewardManager = new RewardManager(new DBAdapter(this));

        txtCA.setText(Double.toString(rewardManager.getCreditPolicyAmount()));
        txtCT.setText(Double.toString(rewardManager.getCreditThreshold()));
        txtDP.setText(Double.toString(rewardManager.getGoldDiscountPercentage()));
        txtGST.setText(Double.toString(rewardManager.getGoldDiscountThreshold()));

//        EditText txt = (EditText) findViewById(R.id.tPrintTrans);
//        txt.setText("Example Text");
//        sRCreditThresholdTxt
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rewards_set, menu);
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

    public void handleRewardsSetB(android.view.View view) {
 //       if (myRM == null) myRM = new RewardManager();
        EditText txtCA = (EditText) findViewById(R.id.sRCreditAmountTxt);
        EditText txtCT = (EditText) findViewById(R.id.sRCreditThresholdTxt);
        EditText txtDP = (EditText) findViewById(R.id.sRDiscountPerTxt);
        EditText txtGST = (EditText) findViewById(R.id.sRGoldStatusThresholdTxt);

        RewardManager rewardManager = new RewardManager(new DBAdapter(this));

        if ((txtCA.getText().toString().length() > 0) && (txtCA.getText().toString() != null)){
            double cA = Double.parseDouble(txtCA.getText().toString());
            rewardManager.setCreditPolicyAmount(cA);
        }

        if ((txtCT.getText().toString().length() > 0) && (txtCT.getText().toString() != null)){
            double cT = Double.parseDouble(txtCT.getText().toString());
            rewardManager.setCreditThreshold(cT);
        }

        if ((txtDP.getText().toString().length() > 0) && (txtDP.getText().toString() != null)){
            double dP = Double.parseDouble(txtDP.getText().toString());
            rewardManager.setGoldDiscountPercentage(dP);
        }

        if ((txtGST.getText().toString().length() > 0) && (txtGST.getText().toString() != null)){
            double gST = Double.parseDouble(txtGST.getText().toString());
            rewardManager.setGoldDiscountThreshold(gST);
        }
        Toast.makeText(getApplicationContext(), "Rewards have been updated.",
                Toast.LENGTH_SHORT).show();
    }
}
