package main.httpclient;

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
    private static JavaNetHttpClient singleton = new JavaNetHttpClient();

    private JavaNetHttpClient() {
    }

    public static JavaNetHttpClient getInstance() {
        return singleton;
    }

    public String fetchInitialURL(String urlString) {
        // htmlパスの場合は、そのままURLを利用する
        if(urlString.endsWith("html")) {
            return urlString;
        }
        String initialUrl = "";
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
                        initialUrl = getInitialPath(reader);
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
        return initialUrl;
    }

    private String getInitialPath(BufferedReader reader) throws IOException {
        String line;
        Integer num = 0;
        String str = "";
        while ((line = reader.readLine()) != null) {
            String trimed = line.trim();
            if (Pattern.compile("^<article").matcher(trimed).find()) {
                num++;
            }
            if (Pattern.compile("^</article").matcher(trimed).find()) {
                num++;
            }
            if(num == 1) {
                String reg = "^.*entryTitle.*href=\"(https://[^\"]+)\".*$";
                if(Pattern.compile(reg).matcher(trimed).find()) {
                    str = trimed.replaceAll(reg, "$1");
                }
            }
        }
        return str;
    }

    public List<String> executeGet(String urlString) {
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
                        getImagePath(list, reader);
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

    private void getImagePath(List<String> list, BufferedReader reader) throws IOException {
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
                // Imageタグ、user_images(メンバーが写っている可能性が高いurl)が入っているか、jpgか
                if(Pattern.compile("<img.*user_images.*jpg").matcher(trimed).find()) {
                    String[] str = trimed.split("<img");
                    for(int i = 1; i < str.length; i++) {
                        // トリミング。txxx_xxxの画像は小さいので、大きい画像に取り替える
                        // jpgをsplitで実装しているのは、正規表現だとNGな場面が発生したため（原因は不明↓のページ）
                        // https://ameblo.jp/angerme-ss-shin/entry-12328153556.html
                        list.add((str[i].replaceAll("^.*src=\"", "").split("jpg")[0] + "jpg").replaceAll("/t\\d+_(\\d+\\.jpg)$", "/o$1"));
                    }
                }
            }
        }
    }

    private void fetchNextUrl(String str, List<String> list) {
        // 次のページのURLが入っているタグ
        if(Pattern.compile("pagingNext").matcher(str).find()) {
            String url = str.replaceAll("^.*href=\"([^\"]+.html)\".*", "$1");
            // https:が付いているか
            if(url.startsWith("//")) {
                list.add("https:" + url);
            } else {
                //そのまま
                list.add(url);
            }
        }
    }
}
