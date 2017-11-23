package main.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {

    private static FileIO singleton = new FileIO();

    private FileIO() {
    }

    public static FileIO getInstance() {
        return singleton;
    }

    /**
     * 前回読んだurlを読み込む
     * @param url
     * @return
     */
    public String readURL(String path, String url) {
        File file = new File(path + "/" + getDomainName(url));
        String lastURL = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            lastURL = br.readLine();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // ファイルがない場合は、空文字列を返却する。
            lastURL = "";
        } catch (IOException e) {
            e.printStackTrace();
            // ファイルがない場合は、空文字列を返却する。
            lastURL = "";
        }
        System.out.println("===== Last URL is " + lastURL + " =====");
        return lastURL;
    }

    /**
     * urlを書き込む
     * @param url
     * @return
     */
    public void writeURL(String url, String path) {
        File file = new File(path + "/" + getDomainName(url));
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(url);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            // 失敗したらなにもしない
        }
        System.out.println("===== " + url + " is writed!! =====");
    }

    /**
     * URLのドメイン名を取得する
     */
    private String getDomainName(String url) {
        if(url.endsWith("html") || url.endsWith("/")) {
            return url.replaceAll("^.*/([^/]+)/[^/]*$", "$1");
        } else {
            return url.replaceAll("^.*/([^/]+)$", "$1");
        }
    }

}
