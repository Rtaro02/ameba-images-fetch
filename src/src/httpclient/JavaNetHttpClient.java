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
    	        System.out.println("===== Connect " + urlString + " =====");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                        BufferedReader reader = new BufferedReader(isr)) {
                        String line;
                        Integer num = 0;
                        while ((line = reader.readLine()) != null) {
                        	String trimed = line.trim();
                        	fetchNextUrl(trimed, list);
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
    	// Imageタグ、user_images(メンバーが写っている可能性が高いurl)が入っているか、jpgか
    	if(Pattern.compile("<img.*user_images.*jpg").matcher(htmlLine).find()) {
    		String[] str = htmlLine.split("<img");
    		for(int i = 1; i < str.length; i++) {
    			// トリミング。txxx_xxxの画像は小さいので、大きい画像に取り替える
    			list.add(str[i].replaceAll("^.*src=\"", "").replaceAll("jpg.*", "jpg").replaceAll("/t\\d+_(\\d+\\.jpg)$", "/o$1"));
    		}        	
    	}
    }
    
    private static void fetchNextUrl(String str, List<String> list) {
    	// 次のページのURLが入っているタグ
    	if(Pattern.compile("skin-pagingNext skin-btnPaging ga-pagingTopNextTop").matcher(str).find()) {
            System.out.println("===== aaa =====");
    			list.add(str.replaceAll("^.*href=\"([^\"]+)\".*", "$1"));
    	}
    }
}