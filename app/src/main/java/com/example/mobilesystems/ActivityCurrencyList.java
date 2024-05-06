package com.example.mobilesystems;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActivityCurrencyList extends AppCompatActivity {
    private ListView listViewCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        listViewCurrencies = findViewById(R.id.list_view_currencies);

        new FetchCurrenciesTask().execute();
    }


    private class FetchCurrenciesTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            try {
                URL url = new URL("https://www.cbr.ru/scripts/XML_daily.asp");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputStream);

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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ActivityCurrencyList.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, currencies);
                listViewCurrencies.setAdapter(adapter);
            } else {
                Log.e("FetchCurrenciesTask", "No currencies found");
            }
        }
    }
}