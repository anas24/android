package com.example.anas.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView w= (WebView)findViewById(R.id.webView);
        w.getSettings().setJavaScriptEnabled(true);
        w.setWebViewClient(new WebViewClient());
        w.loadUrl("http://www.google.com");
    }
}
