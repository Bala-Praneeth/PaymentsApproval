package com.example.vitaly.paymentsapproval.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vitaly.paymentsapproval.R;
import com.example.vitaly.paymentsapproval.model.data.Payment;
import com.example.vitaly.paymentsapproval.view.fragments.PaymentInfoFragment;
import com.example.vitaly.paymentsapproval.view.fragments.PaymentsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ActivityCallback {
    @BindView(R.id.toolbar) Toolbar toolbar;

    private FragmentManager fragmentManager;

    private static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        PreferenceManager.setDefaultValues(this, R.xml.preferences_layout, false);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);

        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        Boolean demo_mode = sharedPref.getBoolean("demo_mode", false);

        if (fragment == null) replaceFragment(new PaymentsListFragment(), false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void replaceFragment (Fragment fragment, Boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if(addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void startPaymentInfoFragment(Payment payment) {
        replaceFragment(PaymentInfoFragment.newInstance(payment), true);
    }

}
