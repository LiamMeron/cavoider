package com.operationcodify.cavoid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.operationcodify.cavoid.R;

public class AppInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
    }

    /**
     * displays the menu in the top toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * switches to the corresponding activity based on the activity selected by the user in the menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(AppInfoActivity.this, SettingsActivity.class);
                this.startActivity(settingsIntent);
                break;
            case R.id.action_appInfo:
                Intent appInfoIntent = new Intent(AppInfoActivity.this, AppInfoActivity.class);
                this.startActivity(appInfoIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}