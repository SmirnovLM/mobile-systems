package com.example.mobilesystems;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActivityCurrencyListJS extends AppCompatActivity {
    private ListView listViewCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list_js);

        listViewCurrencies = findViewById(R.id.list_view_currencies_js);

        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(), "Android");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:(function() {" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', 'https://www.cbr.ru/scripts/XML_daily.asp', false);" +
                        "xhr.send();" +
                        "window.Android.onReceivedXml(xhr.responseText);" +
                        "})()");
            }
        });

        setContentView(webView);
    }

    class JavaScriptInterface {
        @JavascriptInterface
        public void onReceivedXml(String xml) {
            Log.d("ReceivedXml", xml);
            new ParseXmlTask().execute(xml);
        }
    }

    private class ParseXmlTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... strings) {
            try {
                String xmlString = strings[0];
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new ByteArrayInputStream(xmlString.getBytes()));

                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("Valute");

                String[] currencies = new String[nodeList.getLength()];

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String value = element.getElementsByTagName("Value").item(0).getTextContent();

                    currencies[i] = name + ": " + value;
                }

                return currencies;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] currencies) {
            super.onPostExecute(currencies);
            if (currencies != null && currencies.length > 0) {
                Log.d("ParseXmlTask", "Currencies loaded: " + currencies.length);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ActivityCurrencyListJS.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, currencies);
                listViewCurrencies.setAdapter(adapter);
            } else {
                Log.e("ParseXmlTask", "No currencies found");
            }
        }
    }
}