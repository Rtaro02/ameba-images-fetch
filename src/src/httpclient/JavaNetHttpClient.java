package src.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JavaNetHttpClient {

    public static List<String> executeGet(String urlString) {
        System.out.println("===== HTTP GET Start =====");
        List<String> list = new ArrayList<String>();
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
                            	getImagePath(trimed, list);        		
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
        return list;
    }
    
    private static void getImagePath(String htmlLine, List<String> list) {
    	Boolean flg = Pattern.compile("<img.*user_images.*jpg").matcher(htmlLine).find();
    	if(flg) {
    		String[] str = htmlLine.split("<img");
    		for(int i = 1; i < str.length; i++) {
    			list.add(str[i].replaceAll("^.*src=\"", "").replaceAll("jpg.*", "jpg").replaceAll("/t\\d+_(\\d+\\.jpg)$", "/o$1"));
    		}        	
    	}
    }
}