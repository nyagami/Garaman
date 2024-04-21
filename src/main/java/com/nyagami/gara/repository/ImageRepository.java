package com.nyagami.gara.repository;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class ImageRepository {
    private final String token = "6d207e02198a847aa98d0a2a901485a5";
    private final String boundary = "???";
    private static final String LINE_FEED = "\r\n";
    public static ImageRepository instance = null;
    private ImageRepository(){}
    public static ImageRepository getInstance(){
        if(instance == null){
            instance = new ImageRepository();
        }
        return instance;
    }
    public String uploadFile(MultipartFile file) throws IOException {
        URL url = new URL("https://freeimage.host/api/1/upload?key=" + token + "&action=upload");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        OutputStream os = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"source\"; filename=\"")
                .append(file.getName())
                .append("\"")
                .append(LINE_FEED);
        writer.append("Content-Type: ")
                .append(URLConnection.guessContentTypeFromName(file.getName()))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary")
                .append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();
        InputStream is = file.getInputStream();
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0){
            os.write(buffer, 0, len);
        }
        writer.append(LINE_FEED);
        writer.flush();
        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);

        StringBuilder response = new StringBuilder();

        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();

        // checks server's status code first
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getErrorStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            System.out.println(response);
            throw new IOException("Server returned non-OK status: " + status);
        }
        JSONObject json = new JSONObject(response.toString());
        String imageUrl = json.getJSONObject("image").getString("url");
        System.out.println(imageUrl);
        return imageUrl;
    }
}
