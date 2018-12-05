package com.example.rssfeeddisplay;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);


        TextView feedView = findViewById(R.id.RSSContent);
        String RSSFeed = "https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=nfl";
        String RSSTextRaw = "No Connection";

        try {
            RSSTextRaw = getRSSFeed(RSSFeed);
        } catch (IOException e) {
            e.printStackTrace();
        }

        feedView.setText(RSSTextRaw);
        TextView titleFeed = findViewById(R.id.URLAddressLabel);
        titleFeed.setText(RSSFeed);
    }

    public static String getRSSFeed(String RSS_URL) throws IOException {
        String fullFeed = "";
        URL feedURL = new URL(RSS_URL);
        BufferedReader in = new BufferedReader(new InputStreamReader(feedURL.openStream()));
        String line;
        while((line = in.readLine())!=null){
            fullFeed += line;
        }
        in.close();
        return fullFeed;
    }
}
