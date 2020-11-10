package com.operationcodify.cavoid.activities;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.operationcodify.cavoid.R;

import static com.operationcodify.cavoid.activities.MyOnboardingSupportFragment.preferences;

public class OnboardingActivity extends FragmentActivity{
    MyOnboardingSupportFragment fragment = new MyOnboardingSupportFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        TextView title = findViewById(R.id.onboardingTitle);
        title.setText(fragment.getPageTitle(1));
        TextView description = findViewById(R.id.onboardingDescription);
        description.setText(fragment.getPageDescription(1));
        Button onBoardButton = (Button) findViewById(R.id.onboardingButton);
        onBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor sharedPreferencesEditor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                sharedPreferencesEditor.putBoolean(preferences, true);
                sharedPreferencesEditor.apply();
                //fragment.onFinishFragment();
                startActivity(new Intent(OnboardingActivity.this, DashboardActivity.class));
            }
        });
    }
}