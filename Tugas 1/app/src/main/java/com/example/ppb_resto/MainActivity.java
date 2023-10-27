package com.example.ppb_resto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView email, phone, location;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_rm();
        phone_rm();
        location_rm();
    }

    private void email_rm(){
        email = findViewById(R.id.email);
        email.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"alamatemail@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Tanya Seputar Resto");
            intent.putExtra(Intent.EXTRA_TEXT, "Halo, Saya ingin bertanya seputar resto");
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        });
    }

    private void phone_rm(){
        phone = findViewById(R.id.phone);
        phone.setOnClickListener(view -> {
            uri = Uri.parse("tel:08123456789");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });
    }

    private void location_rm(){
        location = findViewById(R.id.location);
        location.setOnClickListener(view -> {
            String longtitude = "-6.9824199750169464";
            String latitude = "110.40916551126774";
            String street = "Universitas+Dian+Nuswantoro";
            uri = Uri.parse(String.format("geo:%s,%s?q=%s", longtitude, latitude, street));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }
}