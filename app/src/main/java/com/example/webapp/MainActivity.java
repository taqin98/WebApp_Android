package com.example.webapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String ShowOrHideWebViewInitialUse = "show";
    ImageView imgLogoSplahScreen;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web     = findViewById(R.id.web_view);
        imgLogoSplahScreen = findViewById(R.id.id_logo);

        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new CustomWebViewClient());

        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.clearCache(true);
        web.setBackgroundColor(0x00000000);


        web.addJavascriptInterface(new WebAppInterface(this){
            @JavascriptInterface
            public void showLoadMember(String text) {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                web.loadUrl(text);
            }
        }, "Android");
        web.loadUrl("https://stgmember.eagles88bet.com/login");
    }

    // This allows for a splash screen
    // (and hide elements once the page loads)
    private class CustomWebViewClient extends WebViewClient {


        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(web.INVISIBLE);
            }
        }


        public void onPageFinished(WebView view, String url) {

            ShowOrHideWebViewInitialUse = "hide";
            imgLogoSplahScreen.setVisibility(View.GONE);

            view.setVisibility(web.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

    @Override
    public void onBackPressed()
    {
        if(web.canGoBack()){
            web.goBack();
            String getWebUrl = new String(web.getUrl());

            Toast.makeText(this, "URL "+getWebUrl, Toast.LENGTH_SHORT).show();

        }else{
            String url = "https://stgmember.eagles88bet.com/MemberLoginMobile";
            String getWebUrl = new String(web.getUrl());

            Toast.makeText(this, "URL "+getWebUrl, Toast.LENGTH_SHORT).show();
            if (!url.equals(getWebUrl)){
                web.loadUrl(url);
                Log.d("TAG", "URL "+url);
            }
            else {
                new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit!")
                    .setMessage("Are you sure you want to close?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
            }

//            if (url.equals(getWebUrl)){
//                new AlertDialog.Builder(this)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setTitle("Exit!")
//                    .setMessage("Are you sure you want to close?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//            }

        }
    }

}


