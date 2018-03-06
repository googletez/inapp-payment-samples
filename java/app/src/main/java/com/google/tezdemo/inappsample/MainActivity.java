package com.google.tezdemo.inappsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private static final int TEZ_REQUEST_CODE = 123;

  private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button payButton = findViewById(R.id.pay_button);
    payButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Uri uri =
            new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", "test@axisbank")
                .appendQueryParameter("pn", "Test Merchant")
                .appendQueryParameter("mc", "1234")
                .appendQueryParameter("tr", "123456789")
                .appendQueryParameter("tn", "test transaction note")
                .appendQueryParameter("am", "10.01")
                .appendQueryParameter("cu", "INR")
                .appendQueryParameter("url", "https://test.merchant.website")
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
        startActivityForResult(intent, TEZ_REQUEST_CODE);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == TEZ_REQUEST_CODE) {
      // Process based on the data in response.
      Log.d("result", data.getStringExtra("Status"));
    }
  }
}
