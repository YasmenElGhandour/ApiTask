package com.example.dell.appssquare.Activity.browsers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.dell.appssquare.R;



public class OwnerBroswer extends AppCompatActivity {

    WebView ownerWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_broswer);


        Intent intent = getIntent();
        String ownerUrl=intent.getStringExtra("owner");


        ownerWebView=(WebView)findViewById(R.id.ownerUrlWB);
        ownerWebView.getSettings().setJavaScriptEnabled(true);
        ownerWebView.loadUrl(ownerUrl);



    }
}
