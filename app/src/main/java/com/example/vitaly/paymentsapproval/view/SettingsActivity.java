package com.example.vitaly.paymentsapproval.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vitaly.paymentsapproval.R;
import com.example.vitaly.paymentsapproval.view.fragments.SettingsFragment;

import butterknife.BindView;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
    }
}
