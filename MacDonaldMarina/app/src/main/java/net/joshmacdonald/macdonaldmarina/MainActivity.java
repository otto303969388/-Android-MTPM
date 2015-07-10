package net.joshmacdonald.macdonaldmarina;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.ProgressDialog;

public class MainActivity extends Activity {

    private static String curWebView;
    private static WebView webView;

    ProgressDialog mProgressDialog;

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.setVisibility(View.GONE);
            mProgressDialog.setTitle(curWebView);
            mProgressDialog.show();
            mProgressDialog.setMessage("Loading...");
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressDialog.dismiss();
            animate(view);
            view.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
        }
    }

    private void animate(final WebView view) {
        Animation anim = AnimationUtils.loadAnimation(getBaseContext(),
                android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new ProgressDialog(this);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        curWebView = "Inventory";
        webView.loadUrl("http://www.macdonaldmarine.ca/");

    }

    public void onInventoryButtonClick(View view){
        if (!(curWebView.equals("Inventory"))){
            curWebView = "Inventory";
            webView.loadUrl("http://www.macdonaldmarine.ca/");
        }
    }

    public void onNorthViewButtonClick(View view){
        if (!(curWebView.equals("North View"))){
            curWebView = "North View";
            webView.loadUrl("http://www.macdonaldmarine.com/North.jpg");
        }
    }

    public void onSouthViewButtonClick(View view){
        if (!(curWebView.equals("South View"))){
            curWebView = "South View";
            webView.loadUrl("http://www.macdonaldmarine.com/South.jpg");
        }
    }

}

