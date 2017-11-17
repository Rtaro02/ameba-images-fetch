package src.ameba;

import java.util.List;

import src.httpclient.GetImage;
import src.httpclient.JavaNetHttpClient;

public class SaveImages {

    public static void saveImages(String initialUrl, String path, Integer num) {
        String url = initialUrl;
        Integer i = 0;
        while(i<num && !url.equals("")) {
            // nextUrl
            url = fetchImages(url, path);
            System.out.println("===== NEXT URL " + url + "=====");
            i++;
        }
        System.out.println("===== END !!=====");
    }

    private static String fetchImages(String url, String path) {
        List<String> list = JavaNetHttpClient.executeGet(url);
        String str = "";
        for(int i=0; i<list.size(); i++) {
            if (list.get(i).endsWith("jpg")) {
                GetImage.fetch(list.get(i), path);
            } else {
                str = list.get(i);
            }
        }
        return str;
    }
}
