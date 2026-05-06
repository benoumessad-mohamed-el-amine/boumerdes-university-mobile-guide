package com.umbb.mobguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.umbb.mobguide.models.Department;

/**
 * Activity to display detailed information and provide contact/location options.
 */
public class DetailActivity extends AppCompatActivity {

    private String name, description, phone, email, locationUri, specialties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads the layout with description, contacts, and map buttons.
        setContentView(R.layout.activity_detail);

        Department data = (Department) getIntent().getSerializableExtra("data");
        name = data.getName();
        description = data.getDescription();
        phone = data.getPhone();
        email = data.getEmail();
        locationUri = data.getLocationUri();
        specialties = data.getSpecialties();

        TextView tvTitle = findViewById(R.id.detail_title);
        TextView tvDescription = findViewById(R.id.detail_description);
        tvTitle.setText(name);
        tvDescription.setText(description);

        LinearLayout specialtiesContainer = findViewById(R.id.specialties_container);
        if (specialties != null && !specialties.isEmpty()) {
            // Displays the specialties section if the data is available.
            specialtiesContainer.setVisibility(View.VISIBLE);
            TextView tvSpecialties = findViewById(R.id.detail_specialties);
            tvSpecialties.setText(specialties);
        }

        findViewById(R.id.btn_call).setOnClickListener(v -> makeCall());
        findViewById(R.id.btn_sms).setOnClickListener(v -> sendSms());
        findViewById(R.id.btn_email).setOnClickListener(v -> sendEmail());
        findViewById(R.id.btn_map).setOnClickListener(v -> openMap());
    }

    private void makeCall() {
        // Launches the phone dialer with the university contact number.
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    private void sendSms() {
        // Opens the SMS application with a pre-filled recipient number.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
        startActivity(intent);
    }

    private void sendEmail() {
        // Starts the email client with the department's email address.
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry: " + name);
        startActivity(intent);
    }

    private void openMap() {
        // Navigates to the faculty location on Google Maps application.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationUri));
        startActivity(intent);
    }
}
