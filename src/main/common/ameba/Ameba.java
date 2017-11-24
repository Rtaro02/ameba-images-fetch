package main.common.ameba;

import java.util.List;

import main.common.util.FetchAndSaveImage;
import main.common.util.FileIO;
import main.httpclient.JavaNetHttpClient;

public class Ameba {

    private static Ameba singleton = new Ameba();

    private Ameba() {
    }

    public static Ameba getInstance() {
        return singleton;
    }

    public void amebaExecute(String initialUrl, String path, Integer upperLimit) {
        // http通信用
        JavaNetHttpClient javaNetHttpClient = JavaNetHttpClient.getInstance();
        // IO用
        FileIO fileIO = FileIO.getInstance();
        
        String url = javaNetHttpClient.fetchInitialURL(initialUrl);
        System.out.println("===== Initial URL is " + url + " =====");
        String lastURL = fileIO.readURL(path, initialUrl);
        // URLを書き込む
        fileIO.writeURL(url, path);
        Integer i = 0;
        while(i<upperLimit && isNotEndPage(url) && isPriviousFetchURL(url, lastURL)) {
            // nextUrl
            url = fetchImages(url, path);
            System.out.println("===== NEXT URL " + url + " =====");
            // 初回のみURL保持
            i++;
        }
        System.out.println("===== END !! =====");
    }

    /**
     * URLが空文字列の場合は最終ページなので、終了する
     * @param url
     * @return
     */
    private boolean isNotEndPage(String url) {
        return !url.equals("");
    }

    /**
     * 前回のURLまでたどり着いた場合は終了する
     * @param url
     * @return
     */
    private boolean isPriviousFetchURL(String url, String lastURL) {
        return !url.equals(lastURL);
    }

    private String fetchImages(String url, String path) {
        List<String> list = JavaNetHttpClient.getInstance().executeGet(url);
        String str = "";
        FetchAndSaveImage FetchAndSaveImages = FetchAndSaveImage.getInstance();
        for(int i=0; i<list.size(); i++) {
            if (list.get(i).endsWith("jpg")) {
                FetchAndSaveImages.fetch(list.get(i), path);
            } else {
                str = list.get(i);
            }
        }
        return str;
    }

}
