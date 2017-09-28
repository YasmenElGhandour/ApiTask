package com.example.dell.appssquare.Activity.browsers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.dell.appssquare.R;


public class HtmlBrowser extends AppCompatActivity {

    WebView htmlWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_browser);

        Intent intent = getIntent();
        String htmlUrl=intent.getStringExtra("html");

        htmlWebView=(WebView)findViewById(R.id.htmlUrlWB);
        htmlWebView.getSettings().setJavaScriptEnabled(true);
        htmlWebView.loadUrl(htmlUrl);


    }
}
