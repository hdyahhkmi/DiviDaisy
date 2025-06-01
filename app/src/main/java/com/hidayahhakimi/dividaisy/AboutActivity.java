package com.hidayahhakimi.dividaisy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up action bar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.about_title);
        }

        // Set up GitHub link click listener with null check
        TextView githubLink = findViewById(R.id.githubLink);
        if (githubLink != null) {
            githubLink.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/hdyahhkmi/DiviDaisy"));
                startActivity(intent);
            });
        }

        // Modern back pressed handling
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                handleBackNavigation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            handleBackNavigation();
            return true;
        }
        else if (id == R.id.menu_home) {
            navigateToHome();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleBackNavigation() {
        finish();
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}