package com.example.webapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

class WebAppInterface {

    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context context) {
        mContext = context;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    //public void getUrl(String url){
    //    return;
    //    Toast.makeText(mContext, "http://192.168.1.2:8961"+url, Toast.LENGTH_SHORT).show();
    //}
}
