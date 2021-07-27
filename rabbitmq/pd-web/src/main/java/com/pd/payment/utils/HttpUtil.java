package com.pd.payment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class HttpUtil {

	private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
	private final static String DEFAULT_ENCODING = "UTF-8";

	public static String postData(String urlStr, String data) {
		return postData(urlStr, data, null);
	}

	public static String postData(String urlStr, String data, String contentType) {
            BufferedReader reader = null;
            OutputStreamWriter writer = null;
            try {
                    URL url = new URL(urlStr);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(CONNECT_TIMEOUT);
                    conn.setReadTimeout(CONNECT_TIMEOUT);
                    if (contentType != null)
                            conn.setRequestProperty("content-type", contentType);
                    writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
                    if (data == null)
                            data = "";
                    writer.write(data);
                    writer.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                            sb.append(line);
                            sb.append("\r\n");
                    }
                    return sb.toString();
            } catch (IOException e) {
                    // logger.error("Error connecting to " + urlStr + ": " +
                    // e.getMessage());
            } finally {
                    try {
                        if (reader != null)
                            reader.close();
                        if (writer != null)
                            writer.close();
                    } catch (IOException e) {
                    }
            }
            return null;
	}
}