package com.flutter_webview_plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
    TextView title;
    private WebViewClient client = new BrowserClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (url.indexOf("http", 0) == 0 || url.indexOf("https", 0) == 0) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
            if (url.indexOf("xjdr", 0) == 0) {
                return true;
            }

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            } catch (Exception e) {
                return true;
            }
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context context, AttributeSet arg1) {
        super(context, arg1);
        this.setWebViewClient(client);
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();
        this.getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }
    /*
     * @Override protected boolean drawChild(Canvas canvas, View child, long
     * drawingTime) { boolean ret = super.drawChild(canvas, child, drawingTime);
     * canvas.save(); Paint paint = new Paint(); paint.setColor(0x7fff0000);
     * paint.setTextSize(24.f); paint.setAntiAlias(true); if
     * (getX5WebViewExtension() != null) {
     * canvas.drawText(this.getContext().getPackageName() + "-pid:" +
     * android.os.Process.myPid(), 10, 50, paint); canvas.drawText( "X5  Core:" +
     * QbSdk.getTbsVersion(this.getContext()), 10, 100, paint); } else {
     * canvas.drawText(this.getContext().getPackageName() + "-pid:" +
     * android.os.Process.myPid(), 10, 50, paint); canvas.drawText("Sys Core", 10,
     * 100, paint); } canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
     * canvas.drawText(Build.MODEL, 10, 200, paint); canvas.restore(); return ret; }
     */

    public X5WebView(Context context) {
        super(context);
        // setBackgroundColor(85621);
    }

}
