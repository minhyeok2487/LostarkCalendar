package lostark.calendar.service;

import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ApiService {

    public InputStreamReader getApi(String link, String apiKey) {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("authorization", "Bearer "+ apiKey);
            httpURLConnection.setRequestProperty("accept","application/json");
            httpURLConnection.setRequestProperty("content-Type","application/json");
            httpURLConnection.setDoOutput(true);

            int result = httpURLConnection.getResponseCode();
            InputStream inputStream;
            if(result == 200) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            return inputStreamReader;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InputStreamReader postApi(String link, String parameter, String apiKey) {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("authorization", "Bearer "+ apiKey);
            httpURLConnection.setRequestProperty("accept","application/json");
            httpURLConnection.setRequestProperty("content-Type","application/json");
            httpURLConnection.setDoOutput(true);

            byte[] out = parameter.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = httpURLConnection.getOutputStream();
            stream.write(out);

            int result = httpURLConnection.getResponseCode();

            InputStream inputStream;
            if(result == 200) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            return inputStreamReader;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
