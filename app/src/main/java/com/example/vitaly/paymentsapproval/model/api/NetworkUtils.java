package com.example.vitaly.paymentsapproval.model.api;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Created by vitaliy on 11/03/2018.
 */

public class NetworkUtils {
    private static final String BASE_URL =  "http://1s-web-dev.office.lamoda.ru/1S-KZ-TOO-Teststand/hs/PaymentsApproval/Applications/1";

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public static String getPaymentInfo() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;
        String username = "**********";
        String password = "**********";

        try {

            Uri buildURI = Uri.parse(BASE_URL);

            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password.toCharArray());
                }
            });

            URL requestURL = new URL(buildURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            if(builder.length() == 0) {
                return null;
            }

            JSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return JSONString;
    }
}
