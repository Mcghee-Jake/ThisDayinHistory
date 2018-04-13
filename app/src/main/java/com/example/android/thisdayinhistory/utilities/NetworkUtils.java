package com.example.android.thisdayinhistory.utilities;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class NetworkUtils {

    final static String BASE_URL = "http://numbersapi.com/";
    final static String TAIL_URL = "/date";

    private static String getDateString() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(today);
    }

    /**
     *  Builds the URL
     * @return The URL
     */
    public static URL buildURL() {

        Uri bultUri = Uri.parse(BASE_URL + getDateString() + TAIL_URL).buildUpon().build();

        URL url = null;

        try {
            url = new URL(bultUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
