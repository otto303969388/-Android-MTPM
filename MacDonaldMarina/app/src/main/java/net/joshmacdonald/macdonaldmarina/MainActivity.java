package net.joshmacdonald.macdonaldmarina;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.ProgressDialog;

public class MainActivity extends Activity {

    private static String curWebView;
    private static WebView webViewInv;
    private static WebView webViewPic;

    ProgressDialog mProgressDialog;

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if( url.startsWith("http:") || url.startsWith("https:") ) {
                view.setVisibility(View.GONE);
                mProgressDialog.setTitle(curWebView);
                mProgressDialog.show();
                mProgressDialog.setMessage("Loading...");
                return false;
            }else{
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
                return true;
            }
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
        webViewInv = (WebView)findViewById(R.id.webViewInv);
        webViewInv.getSettings().setJavaScriptEnabled(true);
        webViewInv.getSettings().setLoadWithOverviewMode(true);
        webViewInv.getSettings().setUseWideViewPort(true);
        webViewInv.setWebViewClient(new MyWebViewClient());
        curWebView = "Inventory";
        webViewInv.loadUrl("http://www.macdonaldmarine.ca/");
        webViewPic = (WebView)findViewById(R.id.webViewPic);
        webViewPic.getSettings().setJavaScriptEnabled(true);
        webViewPic.getSettings().setLoadWithOverviewMode(true);
        webViewPic.getSettings().setUseWideViewPort(true);
        webViewPic.setWebViewClient(new MyWebViewClient());
    }

    public void onInventoryButtonClick(View view){
        if (!(curWebView.equals("Inventory"))){
            webViewPic.setVisibility(View.GONE);
            curWebView = "Inventory";
            webViewInv.loadUrl("http://www.macdonaldmarine.ca/");
        }
    }

    public void onNorthViewButtonClick(View view){
        if (!(curWebView.equals("North View"))){
            webViewInv.setVisibility(View.GONE);
            curWebView = "North View";
            webViewPic.loadUrl("http://www.macdonaldmarine.com/North.jpg");
        }
    }

    public void onSouthViewButtonClick(View view){
        if (!(curWebView.equals("South View"))){
            webViewInv.setVisibility(View.GONE);
            curWebView = "South View";
            webViewPic.loadUrl("http://www.macdonaldmarine.com/South.jpg");

        }
    }

}

