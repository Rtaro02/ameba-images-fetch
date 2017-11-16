package src.httpclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaNetHttpClient {

    public static void executeGet(String urlString) {
        System.out.println("===== HTTP GET Start =====");
        try {
        	// Make URL instance
            URL url = new URL(urlString);

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                                                                       StandardCharsets.UTF_8);
                         BufferedReader reader = new BufferedReader(isr)) {
                        String line;
                        Integer num = 0;
                        while ((line = reader.readLine()) != null) {
                        	String trimed = line.trim();
                        	if (Pattern.compile("^<article").matcher(trimed).find()) {
                        		num++;
                        	}
                        	if (Pattern.compile("^</article").matcher(trimed).find()) {
                        		num++;
                        	}
                        	if(num == 1) {
                            	String str = getImagePath(trimed);
                            	if(str != null) {
                                   System.out.println(str);
                            	}                        		
                        	}
                        }
                    }
                }
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===== HTTP GET End =====");
    }
    
    private static String getImagePath(String htmlLine) {
    	Boolean flg = Pattern.compile("<img.*user_images.*jpg").matcher(htmlLine).find();
    	if(flg) {
        	return htmlLine.replaceAll("^.+<img src=\"", "").replaceAll("jpg.*", "jpg");
    	}
    	return null;
    }
}