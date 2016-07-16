package com.example.anas.techienews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        WebView w=(WebView)findViewById(R.id.webView);
        Intent in =getIntent();
        String url=in.getStringExtra("url");
        w.setWebViewClient(new WebViewClient());
        w.getSettings().setJavaScriptEnabled(true);
        w.loadUrl(url);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                switch (item.getItemId())
                {
                    case android.R.id.home:
                        this.finish();
                        return true;
                    default:

                }
        }
        return super.onOptionsItemSelected(item);
    }

}
