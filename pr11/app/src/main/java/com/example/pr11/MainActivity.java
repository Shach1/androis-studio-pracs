package com.example.pr11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    EditText etUrl;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = (EditText) findViewById(R.id.etUrl);
        webView = (WebView) findViewById(R.id.webView);
        // Помогает приложению открывать ссылки внутри WebView, а не во
        // внешнем браузере
        webView.setWebViewClient(new WebViewClient());
        // Включаем поддержку JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com");
    }

    public void onGoToWebSite(View view)
    {
        String url = etUrl.getText().toString();
        if (url.isEmpty())
        {
            Toast.makeText(this, "Ссылка не введена!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Проверяем, что ссылка начинается с http:// или https://
        if(!url.startsWith("http://") && !url.startsWith("https://"))
        {
            url = "http://" + url;
        }
        try {
            webView.loadUrl(url);
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка при загрузке страницы!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onNextActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}