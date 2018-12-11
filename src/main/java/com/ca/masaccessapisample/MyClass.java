package com.ca.masaccessapisample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class MyClass {

    public void callProtectedServiceWithPOST( String contentType, String endpoint,String payload) {
        byte[] postData = payload.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = endpoint;
        try {
            URL url = new URL(request);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("charset", "utf-8");

            conn.setRequestProperty("Content-Length",
                    Integer.toString(postDataLength));

            conn.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(
                    conn.getOutputStream())) {
                wr.write(postData);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream())); // commit et, response oku.
            String line = "";
            StringBuffer respBuffer=new StringBuffer();
            while ((line = in.readLine()) != null) {
                respBuffer.append(line);
                respBuffer.append("\n");
            }
            conn.disconnect();
            System.out.println(respBuffer.toString());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void callTest() {

    }
}
